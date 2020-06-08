package com.example.login;


import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.Calendar;

import com.example.login.Boundaris.AddressBoundary;
import com.example.login.Boundaris.BirthDate;
import com.example.login.Boundaris.Gender;
import com.example.login.Boundaris.UserBoundary;
import com.example.login.Boundaris.UserRole;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;

public class Register extends AppCompatActivity {


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

    Call<Object> registerCall;


    private static final String TAG ="Register";

    private TextView mDisplayDate;
    private TextView Out;
    private TextView showDate;
    private EditText firstNameTxt;
    private EditText last_Name;
    private EditText id;
    private EditText eMail;
    private EditText zip;
    private EditText phone_;
    private EditText street_address;
    private EditText state_;
    private EditText city_;
    private EditText house_number;
    private EditText passWord;
    private RadioGroup rg;
    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private Button btReg;

    private BirthDate birthDate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mDisplayDate=(TextView) findViewById(R.id.chooseDate);
        showDate=(TextView) findViewById(R.id.setDate);
        firstNameTxt=(EditText) findViewById(R.id.fisrtName) ;
        last_Name=findViewById(R.id.lastName);
        btReg=(Button) findViewById(R.id.btReg);
         rg = (RadioGroup) findViewById(R.id.radio);
         id=findViewById(R.id.userId);
         eMail=findViewById(R.id.email);
         passWord=findViewById(R.id.password);
         zip=findViewById(R.id.zipCode);
         street_address=findViewById(R.id.streetAddress);
         state_=findViewById(R.id.state);
         city_=findViewById(R.id.city);
         house_number=findViewById(R.id.houseNumber);
         phone_=findViewById(R.id.phoneNumber);




        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal =Calendar.getInstance();
                        int year=cal.get(Calendar.YEAR);
                     int month=cal.get(Calendar.MONTH);
                      int day=cal.get(Calendar.DAY_OF_MONTH);

                      DatePickerDialog dialog = new DatePickerDialog(
                              Register.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                              mDateSetListener,year,month,day);
                      dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                      dialog.show();


            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker dataPicker, int year, int month, int dayOfMonth) {
                month=month+1;
                Log.d(TAG,"date : mm/dd/yyyy: "+month +"/" + dayOfMonth +"/" +year);
                String date = month +"/" + dayOfMonth  +"/"+ year;
                showDate.setText(date);
                 birthDate=new BirthDate(dayOfMonth,month,year);
            }
        };


          btReg.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {


        Intent i = new Intent(Register.this, Login.class);
        startActivity(i);


        UserBoundary newUser = validateUser();


        JsonElement userBoundaryJsonElement = new Gson().toJsonTree(newUser);
        registerCall = helpMeAPI.userRegistration(newUser.getUserId(), userBoundaryJsonElement);

//              getUserLoginCall = helpMeAPI.getLogin("alikhxd1@gmail.com", "Ali2862!1");

        httpLoggingInterceptor.setLevel((HttpLoggingInterceptor.Level.BODY));

        registerCall.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

                if(response.isSuccessful()){
                    userStr = response.body().toString();
                    userStr += "";
                    Log.d("USER-STR-REG", userStr);

                    // TODO the user register successfuly and they can go to the main activity
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

    }



    private UserBoundary validateUser(){

        String userID = id.getText().toString();
        String first_name = firstNameTxt.getText().toString();
        String lastName = last_Name.getText().toString();

        String state = state_.getText().toString();
        String city = city_.getText().toString();
        String streetAddress = street_address.getText().toString();
        int zipCode = Integer.parseInt(zip.getText().toString());
        int houseNumber = Integer.parseInt(house_number.getText().toString());
        AddressBoundary address = new AddressBoundary(state, city, streetAddress, houseNumber, zipCode);

        String phone =phone_.getText().toString();
        String email=eMail.getText().toString();
        String password=passWord.getText().toString();

        //get gender
        Gender gender;
        int value= rg.getCheckedRadioButtonId();

        if (value==R.id.FEMALE){
            gender=Gender.FEMALE;
        }
        else{
            gender=Gender.MALE;
        }

        UserBoundary user = new UserBoundary(userID, first_name, lastName, birthDate, gender, phone, address,
                true, null, email, password, UserRole.VOLUNTEER, false);


        return user;
//        return new UserBoundary("205817561", "mahmod", "nassar", new BirthDate(13,01,1995),
//                Gender.MALE, "050-2862222", new AddressBoundary("israel", "tel-aviv", "hamoraim",13, 3081200),
//                true, null, "alikhateeb131@gmail.com", "Ali2862!", UserRole.VOLUNTEER, false);

    }




}
