package com.trv.apptravel.Flight;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.graphics.pdf.PdfDocument;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trv.apptravel.Model.Order.Order;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightFormActivity extends AppCompatActivity {
    AlertDialog dialog;
    AlertDialog.Builder builder;
    String[] items = {"Cetak Tiket"};
    EditText txtFirstname, txtLastname, txtEmail, txtPhonenumber, txtAlamat;
    Button btnConfirm, btnBack;
    TextView txtTitle;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ALAMAT = "alamat";
    private static final String KEY_ID = "id";
    String level, user, alamat, id = "";
    String id_flight,from_flight,to_flight,fromtime_flight,totime_flight, class_flight, price_flight, name_flight = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_flight);
        builder = new AlertDialog.Builder(FlightFormActivity.this);
        builder.setTitle("Pesanan Disimpan");
        txtFirstname = (EditText) findViewById(R.id.txtFirstname);
        txtLastname = (EditText) findViewById(R.id.txtLastname);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        txtPhonenumber = (EditText) findViewById(R.id.txtPhonenumber);
        txtAlamat = (EditText) findViewById(R.id.txtAlamat);
        txtTitle = (TextView) findViewById(R.id.txtTitle);
        btnConfirm = (Button) findViewById(R.id.btnBuy);
        btnBack = (Button) findViewById(R.id.btnBack);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        user = sharedPreferences.getString(KEY_NAMA,null);
        alamat = sharedPreferences.getString(KEY_ALAMAT, null);
        id = sharedPreferences.getString(KEY_ID, null);
        Intent mIntent = getIntent();
        id_flight = mIntent.getStringExtra("id_flight");
        from_flight = mIntent.getStringExtra("from_flight");
        to_flight = mIntent.getStringExtra("to_flight");
        fromtime_flight = mIntent.getStringExtra("fromtime_flight");
        totime_flight = mIntent.getStringExtra("totime_flight");
        class_flight = mIntent.getStringExtra("class_flight");
        price_flight = mIntent.getStringExtra("price_flight");
        name_flight = mIntent.getStringExtra("name_flight");
        if(alamat!=null || alamat!=""){
            txtAlamat.setText(alamat);
        }
        txtTitle.setText(name_flight+" "+"("+from_flight+"->"+to_flight+")");
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlightFormActivity.this, FlightActivity.class);
                startActivity(intent);
            }
        });

        builder.setPositiveButton("Cetak Tiket", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                cetakTicket();
            }
        });

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFirstname.getText().toString().isEmpty() ||
                        txtLastname.getText().toString().isEmpty() ||
                        txtEmail.getText().toString().isEmpty() ||
                        txtPhonenumber.getText().toString().isEmpty() ||
                        txtAlamat.getText().toString().isEmpty()) {
                    Toasty.warning(FlightFormActivity.this, "Isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    buyTicket();
                }

            }
        });

    }

    void buyTicket(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Order> orderCall = mApiInterface.buyFlightTicket(
                txtFirstname.getText().toString(),
                txtLastname.getText().toString(),
                txtEmail.getText().toString(),
                txtAlamat.getText().toString(),
                txtPhonenumber.getText().toString(),
                from_flight,
                to_flight,
                fromtime_flight,
                totime_flight,
                name_flight,
                id,
                price_flight
        );
        orderCall.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    //Toasty.success(getApplicationContext(), "Sukses beli ticket", Toast.LENGTH_SHORT).show();
                    dialog = builder.create();
                    dialog.show();
                    if(!dialog.isShowing()){
                        Intent intent = new Intent(FlightFormActivity.this, FlightActivity.class);
                        startActivity(intent);
                    }
//                    Intent intent = new Intent(FlightFormActivity.this, FlightActivity.class);
//                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal membeli ticket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void cetakTicket() {
        Date dateObj;
        int pageWidth = 1200;
        dateObj = new Date();
        PdfDocument myPdfDocument = new PdfDocument();
        Paint myPaint = new Paint();
        Paint detailPaint = new Paint();
        Paint titlePaint = new Paint();

        PdfDocument.PageInfo myPageInfo1 = new PdfDocument.PageInfo.Builder(1200, 2010, 1).create();
        PdfDocument.Page myPage1 = myPdfDocument.startPage(myPageInfo1);
        Canvas canvas = myPage1.getCanvas();

//                    canvas.drawBitmap(scaledbmp, 0, 0, myPaint);
//                    titlePaint.setTextAlign(Paint.Align.CENTER);
//                    titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
//                    titlePaint.setTextSize(70);
//                    canvas.drawText("Couvee Petshop", pageWidth / 2, 270, titlePaint);

        myPaint.setColor(Color.rgb(0, 113, 188));
        myPaint.setTextSize(30f);
        myPaint.setTextAlign(Paint.Align.LEFT);
        canvas.drawText("TravelYuk", 20, 40, myPaint);
        canvas.drawText("travelyuk123@gmail.com", 20, 80, myPaint);

        titlePaint.setTextAlign(Paint.Align.CENTER);
        titlePaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        titlePaint.setTextSize(60);
        canvas.drawText("TravelYukTicket", pageWidth / 2, 500, titlePaint);

        myPaint.setTextAlign(Paint.Align.LEFT);
        myPaint.setTextSize(40f);
        myPaint.setColor(Color.BLACK);
        canvas.drawText("Dear, "+user, 20, 640, myPaint);
        canvas.drawText("Terima kasih telah menggunakan jasa TravelYuk ", 20, 690, myPaint);
        canvas.drawText("Berikut adalah detail pesanan tiket anda,  ", 20, 740, myPaint);
        detailPaint.setTextAlign(Paint.Align.LEFT);
        detailPaint.setTextSize(40f);
        detailPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.BOLD));
        detailPaint.setColor(Color.BLACK);

        canvas.drawText("Berangkat dari     ",  20, 840, detailPaint);
        canvas.drawText(": "+from_flight,  pageWidth / 2, 840, detailPaint);
        canvas.drawText("Waktu berangkat    ",  20, 890, detailPaint);
        canvas.drawText(": "+fromtime_flight,  pageWidth / 2, 890, detailPaint);
        canvas.drawText("Tiba di            ",20, 940, detailPaint);
        canvas.drawText(": "+to_flight,  pageWidth/2, 940, detailPaint);
        canvas.drawText("Waktu tiba         ", 20, 990, detailPaint);
        canvas.drawText(": "+totime_flight,  pageWidth/2, 990, detailPaint);
        canvas.drawText("Transportasi         ", 20, 1040, detailPaint);
        canvas.drawText(": "+name_flight, pageWidth/2, 1040, detailPaint);
        canvas.drawText("Pesanan atas nama,  ", 20, 1090, myPaint);
        canvas.drawText("Nama               ",20, 1140, detailPaint);
        canvas.drawText(": "+txtFirstname.getText().toString()+" "+txtLastname.getText().toString(), pageWidth/2, 1140, detailPaint);
        canvas.drawText("Nomor HP           ", 20, 1190, detailPaint);
        canvas.drawText(": "+txtPhonenumber.getText().toString(), pageWidth/2, 1190, detailPaint);
        canvas.drawText("Email              ", 20, 1240, detailPaint);
        canvas.drawText(": "+txtEmail.getText().toString(), pageWidth/2, 1240, detailPaint);
        canvas.drawText("Terimakasih  ", 20, 1340, myPaint);
        myPdfDocument.finishPage(myPage1);

        File file = new File(Environment.getExternalStorageDirectory(), "TravelYukTicket.pdf");

        try {
            myPdfDocument.writeTo(new FileOutputStream(file));
            Toasty.success(FlightFormActivity.this, "Tiket anda telah di cetak", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(FlightFormActivity.this, FlightActivity.class);
            startActivity(intent);

        } catch (IOException e) {
            e.printStackTrace();

        }

        myPdfDocument.close();
    }

}
