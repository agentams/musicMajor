package com.example.musicmajor;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.media.audiofx.AudioEffect;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;





import java.util.List;

public class MainActivity extends AppCompatActivity {
    ImageButton b1;

    ImageButton b3;
    ImageButton b4;
    ImageButton b5;
    ImageButton b6;
    ImageButton b7;

    ImageButton b9;
    ImageButton b10;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

       // ActivityCompat.finishAffinity(MainActivity.this);
        b1 = findViewById(R.id.bt1);

        b3 = findViewById(R.id.bt3);
        b4 = findViewById(R.id.bt4);
        b5 = findViewById(R.id.bt5);
        b6 = findViewById(R.id.bt6);
        b7 = findViewById(R.id.bt7);

        b9 = findViewById(R.id.bt9);
        b10 = findViewById(R.id.bt10);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Local Music Files", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,OfflineMusic.class);
                startActivity(intent1 );
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Youtube Music", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,YoutubeMusic.class);
                startActivity(intent1 );
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Spotify Playlist", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,Spotify.class);
                startActivity(intent1 );
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Jio Savan", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,Jiosavan.class);
                startActivity(intent1 );
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Gaana.com", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,Gaana.class);
                startActivity(intent1 );
            }
        });
        b7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening Music Streaming service", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,Streammusic.class);
                startActivity(intent1 );
            }
        });

        b9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Equaliser", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(AudioEffect.ACTION_DISPLAY_AUDIO_EFFECT_CONTROL_PANEL);

                if ((intent.resolveActivity(getPackageManager()) != null)) {
                    startActivityForResult(intent, 0);
                } else {
                    // No equalizer
                    Toast.makeText(MainActivity.this, " No Equaliser Found, want to download one?", Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("https://play.google.com/store/apps/details?id=com.devdnua.equalizer.free");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));
                }
            }
        });
        b10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Opening About.....", Toast.LENGTH_LONG).show();
                Intent intent1= new Intent(MainActivity.this,About.class);
                startActivity(intent1 );
            }
        });


    }
    private Boolean exit = false;
    @Override
    public void onBackPressed() {
        if (exit) {
            finishAffinity();
            finish(); // finish activity
        } else {
            Toast.makeText(this, "Press Back again to Exit.",
                    Toast.LENGTH_SHORT).show();
            exit = true;
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    exit = false;
                }
            }, 3 * 1000);

        }

    }
}