package com.trv.apptravel;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.trv.apptravel.Model.Login.LoginUsers;
import com.trv.apptravel.Network.ApiHelper;
import com.trv.apptravel.Network.ApiInterface;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegistrationActivity extends AppCompatActivity {
    EditText txtUsername, txtPassword, txtConfirmPassword, txtPhonenumber, txtBirthdate, txtEmail;
    TextView txtLogin;
    ImageView imgProfil;
    Button btnSignup, btnOpenCamera;
    String username, password;
    SharedPreferences sharedPreferences;
    private String mediaPath;
    private String postPath;
    private static final int REQUEST_PICK_PHOTO = Setting.REQUEST_PICK_PHOTO;
    private static final int REQUEST_WRITE_PERMISSION = Setting.REQUEST_WRITE_PERMISSION;
    private static final String INSERT_FLAG = Setting.INSERT_FLAG;


    ApiInterface mApiInterface;
    private static final String SHARED_PREF_NAME = "mypref";
    private static final String KEY_LEVEL = "level";
    private static final String KEY_NAMA = "nama";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrasi);
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);
        txtConfirmPassword = (EditText) findViewById(R.id.txtConfirmPassword);
        txtPhonenumber = (EditText) findViewById(R.id.txtPhonenumber);
        txtBirthdate = (EditText) findViewById(R.id.txtBirthdate);
        txtLogin = (TextView) findViewById(R.id.txtLogin);
        txtEmail = (EditText) findViewById(R.id.txtEmail);
        btnSignup = (Button) findViewById(R.id.btnSignup);


        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtUsername.getText().toString().isEmpty() ||
                        txtConfirmPassword.getText().toString().isEmpty() ||
                        txtPassword.getText().toString().isEmpty()||
                        txtEmail.getText().toString().isEmpty() ||
                        txtPhonenumber.getText().toString().isEmpty()||
                        txtBirthdate.getText().toString().isEmpty()){
                        Toasty.warning(RegistrationActivity.this, "Isi data dengan lengkap!", Toast.LENGTH_SHORT).show();
                }else{
                    if(txtConfirmPassword.getText().toString().equalsIgnoreCase(txtPassword.getText().toString())){
                        register();
                    }else{
                        Toasty.warning(RegistrationActivity.this, "Konfirmasi password harus benar !", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        txtLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100) {
            Bitmap captureImage = (Bitmap) data.getExtras().get("data");
            imgProfil.setImageBitmap(captureImage);
        }
    }
//    public void saveImage(){
//        BitmapDrawable drawable = (BitmapDrawable) imgProfil.getDrawable();
//        Bitmap bitmap = drawable.getBitmap();
//        File filepath = Environment.getExternalStorageDirectory();
//        File dir = new File(filepath.getAbsolutePath()+"/Demo/");
//        dir.mkdir();
//        File file = new File
//    }


    void register(){
        ApiInterface apiInterface = ApiHelper.getClient().create(ApiInterface.class);
        Call<LoginUsers> postUsersCall = apiInterface.regisUser(
                txtUsername.getText().toString(),
                txtPassword.getText().toString(),
                txtEmail.getText().toString(),
                txtPhonenumber.getText().toString(),
                txtBirthdate.getText().toString());
        postUsersCall.enqueue(new Callback<LoginUsers>() {
            @Override
            public void onResponse(Call<LoginUsers> call, Response<LoginUsers> response) {
                if(response.isSuccessful()) {
                    Log.d("RETRO", "ON SUCCESS : " + response.message());
                    Toasty.success(RegistrationActivity.this, "Registrasi berhasil ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(intent);
                }
                else {
                    Log.d("RETRO", "ON FAIL : " + response.message());
                    Toasty.warning(RegistrationActivity.this, "Registrasi gagal ", Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            @Override
            public void onFailure(Call<LoginUsers> call, Throwable t) {
                Log.d("RETRO", "ON FAILURE : " + t.getMessage());
                Toasty.error(RegistrationActivity.this, "Registrasi gagal ", Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


}
