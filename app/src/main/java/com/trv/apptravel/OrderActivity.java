package com.trv.apptravel;

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
import com.trv.apptravel.Adapter.OrderAdapter;
import com.trv.apptravel.Model.Laporan.Laporan;
import com.trv.apptravel.Model.Laporan.LaporanList;
import com.trv.apptravel.Model.Order.GetOrder;
import com.trv.apptravel.Model.Order.Order;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;

import java.util.List;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderActivity extends AppCompatActivity {
    TextView txtPrice, txtFromflight,
            txtToflight, txtTimefromflight,
            txtTimetoflight,totalTransaksi, jumlahTransaksi, avgTransaksi;
    ImageView imgMaskapai;
    Button btnBack;
    String level = "";
    SharedPreferences sharedPreferences;
    private RecyclerView mRecyclerView;
    FlightAdapter flightAdapter;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_ID = "id";
    private static final String KEY_NAMA = "nama";
    String nama, id = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        nama = sharedPreferences.getString(KEY_NAMA,null);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        id = sharedPreferences.getString(KEY_ID,null);
        if(level.equals("1")){
            setContentView(R.layout.activity_show_order_admin);
            totalTransaksi = (TextView) findViewById(R.id.totalTransaksi);
            jumlahTransaksi = (TextView) findViewById(R.id.jumlahTransaksi);
            avgTransaksi = (TextView) findViewById(R.id.avgTransaksi);
            showLaporan();
        }else{
            setContentView(R.layout.activity_show_order);
        }
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_order);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        txtPrice = (TextView) findViewById(R.id.txtPrice);
        txtFromflight = (TextView) findViewById(R.id.txtFromflight);
        txtToflight = (TextView) findViewById(R.id.txtToflight);
        txtTimefromflight = (TextView) findViewById(R.id.txtTotimeflight);
        txtTimetoflight = (TextView) findViewById(R.id.txtTotimeflight);

        btnBack = (Button) findViewById(R.id.btnBack);
        if(level.equals("1")){
            listTransaksi();
        }else{
            flight();
        }

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(OrderActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void showLaporan(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<LaporanList> call = mApiInterface.showLaporan();
        call.enqueue(new Callback<LaporanList>() {
            @Override
            public void onResponse(Call<LaporanList> call, Response<LaporanList>
                    response) {
                List<Laporan> getLaporans = response.body().getLaporans();
                totalTransaksi.setText("Rp "+getLaporans.get(0).getTotal_transaksi().toString());
                jumlahTransaksi.setText(""+getLaporans.get(0).getJumlah_transaksi().toString());
                double d= Double.parseDouble(getLaporans.get(0).getAvg_transaksi().toString());
                avgTransaksi.setText(String.format("Rp %.2f", d));

            }

            @Override
            public void onFailure(Call<LaporanList> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(OrderActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void listTransaksi(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Order> call = mApiInterface.showTransaksi();
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order>
                    response) {
                Log.d("Tes", ""+response.body().getGetOrders());
                List<GetOrder> getOrders = response.body().getGetOrders();
                mAdapter = new OrderAdapter(getOrders);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(OrderActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
    public void flight(){
        ApiInterface mApiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Order> call = mApiInterface.showOrder(id);
        call.enqueue(new Callback<Order>() {
            @Override
            public void onResponse(Call<Order> call, Response<Order>
                    response) {
                Log.d("Tes", ""+response.body().getGetOrders());
                List<GetOrder> getOrders = response.body().getGetOrders();
                mAdapter = new OrderAdapter(getOrders);
                mRecyclerView.setAdapter(mAdapter);

            }

            @Override
            public void onFailure(Call<Order> call, Throwable t) {
                Log.e("Retrofit Get", t.toString());
                Toasty.error(OrderActivity.this, "Gagal memuat data tiket  " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
