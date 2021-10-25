package com.trv.apptravel;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.trv.apptravel.Transportation.TransportationActivity;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity {
    Button btnRole, btnLogout, btnTransportation, btnArea, btnUser, btnMyorder;
    TextView txtWelcome;
    String level, nama, alamat= "";
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ALAMAT = "alamat";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        List<SlideModel> slideModels = new ArrayList<>();
//        slideModels.add(new SlideModel("https://youmatter.world/app/uploads/sites/2/2019/11/travel-world.jpg"," ", ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://mythsandmountains.com/wp-content/uploads/2018/08/nepal-1.jpg"," ",ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://www.tripsavvy.com/thmb/BUgCc9waPlXd_THoMaROMNHfiBU=/1500x1000/filters:no_upscale():max_bytes(150000):strip_icc()/Mountain-Travel-Sobek-5a9083a8fa6bcc0037533c31.jpg"," ", ScaleTypes.FIT));
//        slideModels.add(new SlideModel("https://media.istockphoto.com/photos/mid-adult-male-tourist-with-smart-phone-in-barcelona-picture-id1224136047?b=1&k=20&m=1224136047&s=170667a&w=0&h=9LX0mUPIS9cBVPECF1JkkKw-tLqL-Yoep6f76h-zaQk="," ", ScaleTypes.FIT));
//        imageSlider.setImageList(slideModels, ScaleTypes.FIT);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        alamat = sharedPreferences.getString(KEY_ALAMAT,null);
        nama = sharedPreferences.getString(KEY_NAMA,null);
        btnRole = (Button) findViewById(R.id.btnRole);
        btnLogout = (Button) findViewById(R.id.btnLogout);
        btnTransportation = (Button) findViewById(R.id.btnTransportation);
        btnArea = (Button) findViewById(R.id.btnArea);
        btnUser = (Button) findViewById(R.id.btnUser);
        btnMyorder = (Button) findViewById(R.id.btnMyorder);
        txtWelcome = (TextView) findViewById(R.id.txtWelcome);
        if(level.equals("1")){
            btnMyorder.setText("REPORT");
            btnRole.setText("Admin");
        }else{
            btnRole.setText(nama);
        }
        if(alamat == null){
            txtWelcome.setText("Selamat datang kembali, "+nama);
        }else{
            txtWelcome.setText("Selamat datang kembali, "+nama+"\n"+"Alamat : "+alamat);
        }

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Toasty.success(MainActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnTransportation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransportationActivity.class);
                startActivity(intent);
            }
        });
        btnArea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MapsFragment.class);
                startActivity(intent);
            }
        });
        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
        btnMyorder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(intent);
            }
        });
    }
}