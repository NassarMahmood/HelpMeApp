package com.example.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class Notification extends AppCompatActivity {

    ListView listView;
    List list = new ArrayList();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        //Set Profile Selected
        bottomNavigationView.setSelectedItemId(R.id.notification);

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
                        return true;
                    case R.id.report:
                        startActivity(new Intent(getApplicationContext()
                                , Report.class));
                        overridePendingTransition(0, 0);
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

        listView = (ListView) findViewById(R.id.list_view);

        list.add("Martin Ser               0501111111");
        list.add("Mike Selfe               0501234567");
        list.add("Toran Bean              0501227167");
        list.add("Meral Sara               0501352167");

        adapter = new ArrayAdapter(Notification.this,android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);


    }
}
