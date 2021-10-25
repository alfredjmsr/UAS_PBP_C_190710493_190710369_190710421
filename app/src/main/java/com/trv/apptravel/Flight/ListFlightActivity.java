package com.trv.apptravel.Flight;

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

import com.trv.apptravel.Adapter.FlightAdapter;
import com.trv.apptravel.Adapter.ListFlightAdapter;
import com.trv.apptravel.Model.Flight.Flight;
import com.trv.apptravel.Model.Flight.GetFlight;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;
import com.trv.apptravel.R;
import com.trv.apptravel.Transportation.TransportationActivity;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListFlightActivity extends AppCompatActivity {
    Button btnBack, btnCreate;
    String level = "";
    SharedPreferences sharedPreferences;
    private RecyclerView mRecyclerView;
    FlightAdapter flightAdapter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    String nama = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flight);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        nama = sharedPreferences.getString(KEY_NAMA,null);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_listflight);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        btnBack = (Button) findViewById(R.id.btnBack);
        btnCreate = (Button) findViewById(R.id.btnCreate);
        showListFlight();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListFlightActivity.this, TransportationActivity.class);
                startActivity(intent);
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ListFlightActivity.this, CreateFlightActivity.class);
                startActivity(intent);
            }
        });


    }
    public void showListFlight(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Flight> call = mApiInterface.getFlight();
        call.enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight>
                    response) {
                List<GetFlight> getFlights = response.body().getGetFlights();
                mAdapter = new ListFlightAdapter(getFlights);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(ListFlightActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

}
