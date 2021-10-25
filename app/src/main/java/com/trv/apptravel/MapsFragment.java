package com.trv.apptravel;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import es.dmoral.toasty.Toasty;

public class MapsFragment extends AppCompatActivity {
    Button btnBack;
    private GoogleMap mMap;
    LocationManager locationManager;
    LocationListener locationListener;
    LatLng userLatLong;
    SharedPreferences sharedPreferences;
    SupportMapFragment supportMapFragment;
    FusedLocationProviderClient client;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_ALAMAT = "alamat";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        btnBack = (Button) findViewById(R.id.btnBack);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);
        //assign variable
        supportMapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.google_map);
        //Initialize fused location

        //mapFragment.getMapAsync(this);
        client = LocationServices.getFusedLocationProviderClient(this);

        if (ActivityCompat.checkSelfPermission(MapsFragment.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions(MapsFragment.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
        }
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MapsFragment.this, MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void getCurrentLocation() {
        //Inisialisasi task location
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                if(location!=null){
                    supportMapFragment.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("Lokasi anda");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,15));
                            googleMap.addMarker(options);
                            Geocoder geocoder = new Geocoder(getBaseContext(), Locale.getDefault());
                            List<Address> addresses = null;
                            try {
                                addresses = geocoder.getFromLocation(latLng.latitude, latLng.longitude, 1);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                            String cityName = addresses.get(0).getAddressLine(0);
                            String stateName = addresses.get(0).getAddressLine(1);
                            String countryName = addresses.get(0).getAddressLine(2);
                            Toasty.success(MapsFragment.this, "Alamat "+cityName, Toast.LENGTH_SHORT).show();
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString(KEY_ALAMAT,cityName);
                            editor.apply();
                        }
                    });
                }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 44) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getCurrentLocation();
            }
        }
    }

    //    @Override
//    public void onMapReady(GoogleMap googleMap) {
//        mMap = googleMap;
//        locationManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
//        locationListener = new LocationListener() {
//            @Override
//            public void onLocationChanged(@NonNull Location location) {
//                userLatLong = new LatLng(location.getLatitude(), location.getLongitude());
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(userLatLong).title("Lokasi anda"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLong));
//            }
//
//            @Override
//            public void onStatusChanged(String provider, int status, Bundle extras) {
//
//            }
//
//            @Override
//            public void onProviderEnabled(@NonNull String provider) {
//
//            }
//
//            @Override
//            public void onProviderDisabled(@NonNull String provider) {
//
//            }
//        };
//
//        askLocationPermission();
//    }
//
//    public void askLocationPermission() {
//        Dexter.withContext(this).withPermission(Manifest.permission.ACCESS_FINE_LOCATION).withListener(new PermissionListener() {
//            @Override
//            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
//                if (ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getBaseContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                    // TODO: Consider calling
//                    //    ActivityCompat#requestPermissions
//                    // here to request the missing permissions, and then overriding
//                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
//                    //                                          int[] grantResults)
//                    // to handle the case where the user grants the permission. See the documentation
//                    // for ActivityCompat#requestPermissions for more details.
//                    return;
//                }
//                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
//                Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//                userLatLong = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(userLatLong).title("Lokasi anda"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(userLatLong));
//            }
//
//            @Override
//            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {
//
//            }
//
//            @Override
//            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
//                permissionToken.continuePermissionRequest();
//            }
//        }).check();
//    }
}