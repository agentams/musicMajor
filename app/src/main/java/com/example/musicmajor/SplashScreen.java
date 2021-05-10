package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.content.Intent;
import android.os.Bundle;

import java.util.Timer;
import java.util.TimerTask;

public class SplashScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // ActivityCompat.finishAffinity(SplashScreen.this);

        new Timer().schedule(new TimerTask(){
            public void run() {
                startActivity(new Intent(SplashScreen.this,MainActivity.class));
            }
        }, 2000);
    }



}
