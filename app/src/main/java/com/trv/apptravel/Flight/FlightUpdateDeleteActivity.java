package com.trv.apptravel.Flight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trv.apptravel.Model.Flight.GetFlight;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FlightUpdateDeleteActivity extends AppCompatActivity {
    EditText txtFromflight, txtToflight, txtFromtimeflight, txtTotimeflight, txtFlightClass, txtPlane, txtPrice;
    Button btnCreate, btnBack, btnHapus;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    String level, user = "";
    String id_flight,from_flight,to_flight,fromtime_flight,totime_flight, class_flight, price_flight, name_flight = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_flight);
        txtFromflight = (EditText) findViewById(R.id.txtFromflight);
        txtToflight = (EditText) findViewById(R.id.txtToflight);
        txtFromtimeflight = (EditText) findViewById(R.id.txtFromtimeflight);
        txtTotimeflight = (EditText) findViewById(R.id.txtTotimeflight);
        txtFlightClass = (EditText) findViewById(R.id.txtFlightClass);
        txtPlane = (EditText) findViewById(R.id.txtPlane);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnHapus = (Button) findViewById(R.id.btnHapus);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        user = sharedPreferences.getString(KEY_NAMA,null);
        Intent mIntent = getIntent();
        id_flight = mIntent.getStringExtra("id_flight");
        from_flight = mIntent.getStringExtra("from_flight");
        to_flight = mIntent.getStringExtra("to_flight");
        fromtime_flight = mIntent.getStringExtra("fromtime_flight");
        totime_flight = mIntent.getStringExtra("totime_flight");
        class_flight = mIntent.getStringExtra("class_flight");
        price_flight = mIntent.getStringExtra("price_flight");
        name_flight = mIntent.getStringExtra("name_flight");
        txtFromflight.setText(from_flight);
        txtToflight.setText(to_flight);
        txtFromtimeflight.setText(fromtime_flight);
        txtTotimeflight.setText(totime_flight);
        txtFlightClass.setText(class_flight);
        txtPlane.setText(name_flight);
        txtPrice.setText(price_flight);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlightUpdateDeleteActivity.this, ListFlightActivity.class);
                startActivity(intent);
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteTicket();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtFromflight.getText().toString().isEmpty() ||
                        txtToflight.getText().toString().isEmpty() ||
                        txtFromtimeflight.getText().toString().isEmpty() ||
                        txtTotimeflight.getText().toString().isEmpty() ||
                        txtFlightClass.getText().toString().isEmpty() ||
                        txtPlane.getText().toString().isEmpty() ||
                        txtPrice.getText().toString().isEmpty()
                        ) {
                    Toasty.warning(FlightUpdateDeleteActivity.this, "Isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    updateTicket();
                }

            }
        });

    }

    void updateTicket(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetFlight> orderCall = mApiInterface.updateflights(
                id_flight,
                txtFromflight.getText().toString(),
                txtToflight.getText().toString(),
                txtFromtimeflight.getText().toString(),
                txtTotimeflight.getText().toString(),
                txtPrice.getText().toString(),
                txtFlightClass.getText().toString(),
                txtPlane.getText().toString()
        );
        orderCall.enqueue(new Callback<GetFlight>() {
            @Override
            public void onResponse(Call<GetFlight> call, Response<GetFlight> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(getApplicationContext(), "Sukses update ticket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FlightUpdateDeleteActivity.this, ListFlightActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal update ticket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFlight> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    void deleteTicket(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetFlight> orderCall = mApiInterface.deleteflights(
                id_flight
        );
        orderCall.enqueue(new Callback<GetFlight>() {
            @Override
            public void onResponse(Call<GetFlight> call, Response<GetFlight> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(getApplicationContext(), "Sukses delete ticket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(FlightUpdateDeleteActivity.this, ListFlightActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal delete ticket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetFlight> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

}
