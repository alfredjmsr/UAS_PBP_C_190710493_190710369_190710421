package com.trv.apptravel;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.trv.apptravel.Model.Login.Login;
import com.trv.apptravel.Model.Login.LoginUsers;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;

import java.io.File;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    EditText txtUsername, txtPhonenumber, txtBirthdate;
    ImageView iv_gambar;
    Button btnBack, btnUpdate, btnLogout, btnGallery;
    String level, nama;
    SharedPreferences sharedPreferences;
    String id = "";
    private String mediaPath;
    private String postPath;
    private ImageView ivGambar;
    private LinearLayout layoutLoading;
    private Bitmap bitmap = null;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";
    private static final String KEY_ID = "id";
    private static final int REQUEST_PICK_PHOTO = Setting.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Setting.REQUEST_WRITE_PERMISSION;
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        if (requestCode == REQUEST_WRITE_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            updateProfile();
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPhonenumber = (EditText) findViewById(R.id.txtPhonenumber);
        txtBirthdate = (EditText) findViewById(R.id.txtBirthdate);
        btnBack    = (Button) findViewById(R.id.btnBack);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        ivGambar = (ImageView) findViewById(R.id.iv_gambar);
        btnLogout    = (Button) findViewById(R.id.btnLogout);
        btnGallery    = (Button) findViewById(R.id.btnGallery);
        sharedPreferences = getSharedPreferences(SHARED_PREF_NAME,MODE_PRIVATE);
        level = sharedPreferences.getString(KEY_LEVEL,null);
        id = sharedPreferences.getString(KEY_ID,null);
        nama = sharedPreferences.getString(KEY_NAMA,null);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences preferences =getSharedPreferences("mypref", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.clear();
                editor.apply();
                Toasty.success(ProfileActivity.this, "Berhasil Logout", Toast.LENGTH_SHORT).show();
                finish();
                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnGallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(galleryIntent, REQUEST_PICK_PHOTO);
            }
        });


        profile();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            if(requestCode == REQUEST_PICK_PHOTO){
                if(data!=null){
                    Uri selectedImage = data.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};

                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();

                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    mediaPath = cursor.getString(columnIndex);
                    ivGambar.setImageURI(data.getData());
                    cursor.close();

                    postPath = mediaPath;
                }
            }
        }
    }


    //Tampilkan Profile
    public void profile(){
        ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<Login> postUsersCall = apiInterface.getUser(nama);
        postUsersCall.enqueue(new Callback<Login>() {
            @Override
            public void onResponse(Call<Login> call, Response<Login> response) {
                if(response.isSuccessful()) {
                    List<LoginUsers> usersList = response.body().getLoginUsers();
                    String phonenumber = usersList.get(0).getPhonenumber_user();
                    String birthdate = usersList.get(0).getBirthdate_user();
                    String images = usersList.get(0).getImage_user();
                    Glide.with(ProfileActivity.this)
                                .load(Setting.IMAGES_URL + images)
                                .apply(new RequestOptions().override(350, 550))
                                .apply(RequestOptions.circleCropTransform())
                                .into(ivGambar);
                    txtUsername.setText(nama);
                        txtPhonenumber.setText(phonenumber);
                        txtBirthdate.setText(birthdate);


                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.info(ProfileActivity.this, "Gagal memuat profil !", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Login> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(ProfileActivity.this, "Gagal Login !", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void updateProfile() {
        ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
        if (mediaPath == null) {
            Toasty.info(getApplicationContext(), "Gambar belum di pilih", Toast.LENGTH_LONG).show();
        } else {
            File imagefile = new File(mediaPath);
            RequestBody reqBody = RequestBody.create(MediaType.parse("multipart/form-file"), imagefile);
            MultipartBody.Part imageUser = MultipartBody.Part.createFormData("image_user", imagefile.getName(), reqBody);

            Call<LoginUsers> postUsersCall = apiInterface.updateUser(
                    imageUser,
                    RequestBody.create(MediaType.parse("text/plain"), id),
                    RequestBody.create(MediaType.parse("text/plain"), txtUsername.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), txtPhonenumber.getText().toString()),
                    RequestBody.create(MediaType.parse("text/plain"), txtBirthdate.getText().toString()));

            postUsersCall.enqueue(new Callback<LoginUsers>() {
                @Override
                public void onResponse(Call<LoginUsers> call, Response<LoginUsers> response) {
                    if (response.isSuccessful()) {
                        Toasty.success(ProfileActivity.this, "Update profile berhasil ", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                        startActivity(intent);
                    } else {
                        Log.d("RETRO", "ON FAIL : " + response.message());
                        Toasty.warning(ProfileActivity.this, "Update profile gagal ", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }

                @Override
                public void onFailure(Call<LoginUsers> call, Throwable t) {
                    Toasty.error(ProfileActivity.this, "Update profile gagal ", Toast.LENGTH_SHORT).show();
                    return;
                }
            });
        }
    }

    private void requestPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, REQUEST_WRITE_PERMISSION);
        } else {
            updateProfile();
        }
    }


}
