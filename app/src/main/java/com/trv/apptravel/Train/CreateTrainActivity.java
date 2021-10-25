package com.trv.apptravel.Train;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trv.apptravel.Model.Train.GetTrain;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateTrainActivity extends AppCompatActivity {
    EditText txtFromflight, txtToflight, txtFromtimeflight, txtTotimeflight, txtFlightClass, txtPlane, txtPrice;
    Button btnCreate, btnBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_train);
        txtFromflight = (EditText) findViewById(R.id.txtFromflight);
        txtToflight = (EditText) findViewById(R.id.txtToflight);
        txtFromtimeflight = (EditText) findViewById(R.id.txtFromtimeflight);
        txtTotimeflight = (EditText) findViewById(R.id.txtTotimeflight);
        txtFlightClass = (EditText) findViewById(R.id.txtFlightClass);
        txtPlane = (EditText) findViewById(R.id.txtPlane);
        txtPrice = (EditText) findViewById(R.id.txtPrice);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnCreate = (Button) findViewById(R.id.btnCreate);

        //Buka gallery
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateTrainActivity.this, ListTrainActivity.class);
                startActivity(intent);
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
                        txtPrice.getText().toString().isEmpty()) {
                    Toasty.warning(CreateTrainActivity.this, "Isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    addTrain();
                }

            }
        });
        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY;
        decorView.setSystemUiVisibility(uiOptions);
    }

    void addTrain(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetTrain> call = mApiInterface.addTrain(txtFromflight.getText().toString(),
                txtToflight.getText().toString(),
                txtFromtimeflight.getText().toString(),
                txtTotimeflight.getText().toString(),
                txtPrice.getText().toString(),
                txtFlightClass.getText().toString(),
                txtPlane.getText().toString());
        call.enqueue(new Callback<GetTrain>() {
            @Override
            public void onResponse(Call<GetTrain> call, Response<GetTrain> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(getApplicationContext(), "Sukses menambahkan tiket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(CreateTrainActivity.this, ListTrainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal menambahkan tiket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTrain> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

}