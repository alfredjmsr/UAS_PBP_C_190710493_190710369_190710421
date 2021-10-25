package com.trv.apptravel.Train;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trv.apptravel.Adapter.ListTrainAdapter;
import com.trv.apptravel.Adapter.TrainAdapter;
import com.trv.apptravel.Model.Train.GetTrain;
import com.trv.apptravel.Model.Train.Train;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;
import com.trv.apptravel.Transportation.TransportationActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListTrainActivity extends AppCompatActivity {
    Button btnBack, btnCreate;
    String level = "";
    SharedPreferences sharedPreferences;
    private RecyclerView mRecyclerView;
    TrainAdapter trainAdapter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    String nama = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_train);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        nama = sharedPreferences.getString(KEY_NAMA,null);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_listtrain);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        showListTrain();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTrainActivity.this, TransportationActivity.class);
                startActivity(intent);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListTrainActivity.this, CreateTrainActivity.class);
                startActivity(intent);
            }
        });


    }
    public void showListTrain(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Train> call = mApiInterface.getTrain();
        call.enqueue(new Callback<Train>() {
            @Override
            public void onResponse(Call<Train> call, Response<Train>
                    response) {
                List<GetTrain> getTrains = response.body().getGetTrains();
                mAdapter = new ListTrainAdapter(getTrains);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Train> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(ListTrainActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
