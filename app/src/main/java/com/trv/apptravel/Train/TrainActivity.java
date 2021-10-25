package com.trv.apptravel.Train;

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

public class TrainActivity extends AppCompatActivity {
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
        setContentView(R.layout.activity_show_train);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_train);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtFromflight = (TextView) findViewById(R.id.txtFromflight);
        txtToflight = (TextView) findViewById(R.id.txtToflight);
        txtTimefromflight = (TextView) findViewById(R.id.txtTotimeflight);
        txtTimetoflight = (TextView) findViewById(R.id.txtTotimeflight);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        btnBack = (Button) findViewById(R.id.btnBack);

        train();
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TrainActivity.this, TransportationActivity.class);
                startActivity(intent);
            }
        });

    }

    public void train(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Train> call = mApiInterface.getTrain();
        call.enqueue(new Callback<Train>() {
            @Override
            public void onResponse(Call<Train> call, Response<Train>
                    response) {
                Log.d("Tes", ""+response.body().getGetTrains());
                List<GetTrain> getTrainList = response.body().getGetTrains();
                Log.d("Tes2", ""+getTrainList.get(0).getNametrain());
                mAdapter = new TrainAdapter(getTrainList);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Train> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(TrainActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
