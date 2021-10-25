package com.trv.apptravel.Train;

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

import com.trv.apptravel.Model.Train.GetTrain;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TrainUpdateDeleteActivity extends AppCompatActivity {
    EditText txtFromtrain, txtTotrain, txtFromtimetrain, txtTotimetrain, txttrainClass, txtPlane, txtPrice;
    Button btnCreate, btnBack, btnHapus;
    SharedPreferences sharedPreferences;
    ApiInterface mApiInterface;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    String level, user = "";
    String id_train,from_train,to_train,fromtime_train,totime_train, class_train, price_train, name_train = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_train);
        txtFromtrain = (EditText) findViewById(R.id.txtFromtrain);
        txtTotrain = (EditText) findViewById(R.id.txtTotrain);
        txtFromtimetrain = (EditText) findViewById(R.id.txtFromtimetrain);
        txtTotimetrain = (EditText) findViewById(R.id.txtTotimetrain);
        txttrainClass = (EditText) findViewById(R.id.txttrainClass);
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
        id_train = mIntent.getStringExtra("id_train");
        from_train = mIntent.getStringExtra("from_train");
        to_train = mIntent.getStringExtra("to_train");
        fromtime_train = mIntent.getStringExtra("fromtime_train");
        totime_train = mIntent.getStringExtra("totime_train");
        class_train = mIntent.getStringExtra("class_train");
        price_train = mIntent.getStringExtra("price_train");
        name_train = mIntent.getStringExtra("name_train");
        txtFromtrain.setText(from_train);
        txtTotrain.setText(to_train);
        txtFromtimetrain.setText(fromtime_train);
        txtTotimetrain.setText(totime_train);
        txttrainClass.setText(class_train);
        txtPlane.setText(name_train);
        txtPrice.setText(price_train);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainUpdateDeleteActivity.this, ListTrainActivity.class);
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
                if (txtFromtrain.getText().toString().isEmpty() ||
                        txtTotrain.getText().toString().isEmpty() ||
                        txtFromtimetrain.getText().toString().isEmpty() ||
                        txtTotimetrain.getText().toString().isEmpty() ||
                        txttrainClass.getText().toString().isEmpty() ||
                        txtPlane.getText().toString().isEmpty() ||
                        txtPrice.getText().toString().isEmpty()
                ) {
                    Toasty.warning(TrainUpdateDeleteActivity.this, "Isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                } else {
                    updateTicket();
                }

            }
        });

    }

    void updateTicket(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetTrain> orderCall = mApiInterface.updatetrains(
                id_train,
                txtFromtrain.getText().toString(),
                txtTotrain.getText().toString(),
                txtFromtimetrain.getText().toString(),
                txtTotimetrain.getText().toString(),
                txtPrice.getText().toString(),
                txttrainClass.getText().toString(),
                txtPlane.getText().toString()
        );
        orderCall.enqueue(new Callback<GetTrain>() {
            @Override
            public void onResponse(Call<GetTrain> call, Response<GetTrain> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(getApplicationContext(), "Sukses update ticket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TrainUpdateDeleteActivity.this, ListTrainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal update ticket", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<GetTrain> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
            }
        });
    }

    void deleteTicket(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<GetTrain> orderCall = mApiInterface.deletetrains(
                id_train
        );
        orderCall.enqueue(new Callback<GetTrain>() {
            @Override
            public void onResponse(Call<GetTrain> call, Response<GetTrain> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(getApplicationContext(), "Sukses delete ticket", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(TrainUpdateDeleteActivity.this, ListTrainActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.error(getApplicationContext(), "Gagal delete ticket", Toast.LENGTH_SHORT).show();
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
