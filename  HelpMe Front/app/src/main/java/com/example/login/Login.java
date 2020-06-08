package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.login.Boundaris.UserBoundary;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Login extends AppCompatActivity {


    String userStr = "";

    HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .addInterceptor(httpLoggingInterceptor)
            .build();

    Gson userModelGsonBuilder = new GsonBuilder()
            .registerTypeAdapter(UserBoundary.class, new UserModelDeserializer())
            .create();

    Retrofit userBoundariesRetrofit = new Retrofit.Builder()
            .baseUrl("http://10.0.0.12:2345/")
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(userModelGsonBuilder))
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    HelpMeAPI helpMeAPI = userBoundariesRetrofit.create(HelpMeAPI.class);

    Call<Object> getUserLoginCall;

    private Button btnLogin;
    private TextView tvReg;
    private EditText userEmail;
    private EditText password;

    private static int TIME_OUT = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userEmail=(EditText) findViewById(R.id.email) ;
        password=(EditText) findViewById(R.id.password) ;

        btnLogin = findViewById(R.id.Login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(Login.this, MainActivity.class);
                startActivity(i);

                String userEmailStr = userEmail.getText().toString();
                String passwordStr = password.getText().toString();

                getUserLoginCall = helpMeAPI.getLogin(userEmailStr, passwordStr);
//              getUserLoginCall = helpMeAPI.getLogin("alikhxd1@gmail.com", "Ali2862!1");

                httpLoggingInterceptor.setLevel((HttpLoggingInterceptor.Level.BODY));

                getUserLoginCall.enqueue(new Callback<Object>() {
                    @Override
                    public void onResponse(Call<Object> call, Response<Object> response) {

                        if(response.isSuccessful()){
                            userStr = response.body().toString();
                            userStr += "";
                            Log.d("USER-STR", userStr);

                            // TODO the user login successfuly and they can go to the main activity


                        }
                        else
                        {
                            //okhttp
                            // TODO handle the error and show it to user
                        }
                    }
                    @Override
                    public void onFailure(Call<Object> call, Throwable t) {

                    }
                });

            }
        });


        tvReg = findViewById(R.id.tvReg);
        tvReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);

            }
        });


    }


}
