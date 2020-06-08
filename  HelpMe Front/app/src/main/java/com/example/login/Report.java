package com.example.login;


import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.login.Boundaris.AddressBoundary;
import com.example.login.Boundaris.BirthDate;
import com.example.login.Boundaris.Gender;
import com.example.login.Boundaris.IndigentBoundary;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Calendar;
import java.util.Date;

public class Report extends AppCompatActivity {





    Button btSend;
    private TextView mDisplayDate;

    private DatePickerDialog.OnDateSetListener mDateSetListener;
    private BirthDate birthDate;


    RadioGroup rg;
    Gender gender;


    private TextView showDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);


        mDisplayDate=(TextView) findViewById(R.id.chooseDate);
        showDate=(TextView) findViewById(R.id.setDate);





        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set Profile Selected
        bottomNavigationView.setSelectedItemId(R.id.report);


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {


            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {

                    case R.id.profile:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.associations:
                        startActivity(new Intent(getApplicationContext()
                                , Association.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.notification:
                        startActivity(new Intent(getApplicationContext()
                                , Notification.class));
                        overridePendingTransition(0, 0);
                        return true;
                    case R.id.report:
                        return true;
                    case R.id.setting:
                        startActivity(new Intent(getApplicationContext()
                                , Setting.class));
                        overridePendingTransition(0, 0);
                        return true;
                }
                return false;
            }
        });


        mDisplayDate=(TextView) findViewById(R.id.chooseDate);


        mDisplayDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal =Calendar.getInstance();
                int year=cal.get(Calendar.YEAR);
                int month=cal.get(Calendar.MONTH);
                int day=cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        Report.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();


            }
        });
        mDateSetListener=new DatePickerDialog.OnDateSetListener(){

            @Override
            public void onDateSet(DatePicker dataPicker, int year, int month, int dayOfMonth) {
                month=month+1;

                // Log.d(TAG,"date : mm/dd/yyyy: "+month +"/" + dayOfMonth +"/" +year);
                String date = month +"/" + dayOfMonth  +"/"+ year;
                showDate.setText(date);
                birthDate=new BirthDate(dayOfMonth,month,year);
            }
        };

        btSend = findViewById(R.id.button2);

        btSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // startActivity(new Intent(Report.this,Report.class));

                IndigentBoundary a ;
                EditText id= findViewById(R.id.indigentId);
                EditText firsName= findViewById(R.id.firstName);
                EditText lastName= findViewById(R.id.lastName);
                EditText city= findViewById(R.id.city);
                EditText zip= findViewById(R.id.zipCode);
                EditText streetAddress= findViewById(R.id.streetAddress);
                EditText houseNumber= findViewById(R.id.houseNumber);
                EditText state= findViewById(R.id.state);
                EditText eatDay= findViewById(R.id.eatDays);
                rg =  findViewById(R.id.radio);
                EditText phone= findViewById(R.id.phone);
                EditText note= findViewById(R.id.note);

                //get gender
                int value= rg.getCheckedRadioButtonId();


                if (value==R.id.FEMALE){
                    gender=Gender.FEMALE;
                }
                else{
                    gender= Gender.MALE;
                }


                // get zipCode
                int zip_ =0;
                zip_=Integer.parseInt(zip.getText().toString());

                // get house number
                int houseNumber_ =0;
                houseNumber_=Integer.parseInt(houseNumber.getText().toString());

                int eatDays_ =0;
                eatDays_=Integer.parseInt(eatDay.getText().toString());


                AddressBoundary address= new AddressBoundary(state.getText().toString(),
                        city.getText().toString(),streetAddress.getText().toString()
                        ,houseNumber_,zip_);


                a=new IndigentBoundary(id.getText().toString(),firsName.getText().toString(),lastName.getText().toString(),birthDate,
                        gender,phone.getText().toString(),address,false,null,null,eatDays_,note.getText().toString());

                Log.d("myTag",a.getFisrtName());
            }

        });


    }




}
