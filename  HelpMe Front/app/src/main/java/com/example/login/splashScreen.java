package com.example.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class splashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        LogoLuncher l = new LogoLuncher();
        l.start();




    }
    private class LogoLuncher extends Thread {
        public void run() {

            try {
                sleep(5000);

            }
            catch (InterruptedException e ){
                e.printStackTrace();
            }
            startActivity(new Intent(splashScreen.this , Login.class));
            splashScreen.this.finish();

        }
    }
}
