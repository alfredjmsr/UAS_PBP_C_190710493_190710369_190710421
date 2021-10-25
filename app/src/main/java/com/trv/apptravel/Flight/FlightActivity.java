package com.trv.apptravel.Flight;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.trv.apptravel.Adapter.FlightAdapter;
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

public class FlightActivity extends AppCompatActivity {
    TextView txtPrice, txtFromflight,
            txtToflight, txtTimefromflight,
            txtTimetoflight;
    ImageView imgMaskapai;
    Button btnBack;
    SharedPreferences sharedPreferences;
    private RecyclerView mRecyclerView;
    FlightAdapter flightAdapter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_flight);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_flight);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtFromflight = (TextView) findViewById(R.id.txtFromflight);
        txtToflight = (TextView) findViewById(R.id.txtToflight);
        txtTimefromflight = (TextView) findViewById(R.id.txtTotimeflight);
        txtTimetoflight = (TextView) findViewById(R.id.txtTotimeflight);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        btnBack = (Button) findViewById(R.id.btnBack);

        flight();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(FlightActivity.this, TransportationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void flight(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Flight> call = mApiInterface.getFlight();
        call.enqueue(new Callback<Flight>() {
            @Override
            public void onResponse(Call<Flight> call, Response<Flight>
                    response) {
                Log.d("Tes", ""+response.body().getGetFlights());
                List<GetFlight> flightList = response.body().getGetFlights();
                Log.d("Tes2", ""+flightList.get(0).getNameFlight());
                mAdapter = new FlightAdapter(flightList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Flight> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(FlightActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
