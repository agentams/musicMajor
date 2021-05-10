package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.audiofx.AudioEffect;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class About extends AppCompatActivity {
    ImageButton inb;

    ImageButton teleb;
    ImageButton gitb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        inb = findViewById(R.id.inb);
        teleb = findViewById(R.id.teleb);
        gitb = findViewById(R.id.gitb);

        inb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                    Toast.makeText(About.this, "follow me on linked in", Toast.LENGTH_SHORT).show();
                    Uri uri = Uri.parse("https://www.linkedin.com/in/ams0704/");
                    startActivity(new Intent(Intent.ACTION_VIEW, uri));

            }
        });
        gitb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(About.this, "follow me on GitHub", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("https://github.com/agentams");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

            }
        });
        teleb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(About.this, "Lest's chat on telegram", Toast.LENGTH_SHORT).show();
                Uri uri = Uri.parse("https://t.me/AgEnTaMs");
                startActivity(new Intent(Intent.ACTION_VIEW, uri));

            }
        });

    }
}