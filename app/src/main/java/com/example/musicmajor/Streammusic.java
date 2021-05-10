package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.NotificationCompat;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import com.example.jean.jcplayer.model.JcAudio;
import com.example.jean.jcplayer.view.JcPlayerView;

import java.util.ArrayList;

public class Streammusic extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_streammusic);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        if (isOnline()) {
            String url1 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Brotherhood.mp3?alt=media&token=2f911ac1-8d8c-4108-97ba-1c0204214915";
            String url2 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Despacito.mp3?alt=media&token=a36e7241-4cd2-41b4-9439-875775dcda63";
            String url3 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Mi_Gente.mp3?alt=media&token=b4b61f61-e520-4b31-9707-d1607b5cab46";
            String url4 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Suit.mp3?alt=media&token=f502f215-60d8-458c-aa8e-bc33bf7ac53d";
            String url5 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Till_I_Collapse.mp3?alt=media&token=9ecdba71-f21f-40fd-82fc-787369ab6089";
            String url6 = "https://firebasestorage.googleapis.com/v0/b/music-app-26fd7.appspot.com/o/Tokyo_Drift.mp3?alt=media&token=0b9b2abe-e390-4fdc-b9fb-0c09db14f4e7";

            JcPlayerView jcPlayerView = (JcPlayerView) findViewById(R.id.jcplayer);

            ArrayList<JcAudio> jcAudios = new ArrayList<>();
            jcAudios.add(JcAudio.createFromURL("Brotherhood", url1));
            jcAudios.add(JcAudio.createFromURL("Despacito", url2));
            jcAudios.add(JcAudio.createFromURL("Mi Gente", url3));
            jcAudios.add(JcAudio.createFromURL("Suit", url4));
            jcAudios.add(JcAudio.createFromURL("Till I Collapse", url5));
            jcAudios.add(JcAudio.createFromURL("Tokyo Drift", url6));

            jcPlayerView.initPlaylist(jcAudios, null);
            jcPlayerView.createNotification(R.drawable.streammusic);



        } else {
            try {
                new AlertDialog.Builder(Streammusic.this)
                        .setTitle("Error")
                        .setMessage("Internet not available, Cross check your internet connectivity and try again later...")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setNeutralButton("OK", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                finish();

                            }
                        }).show();
            } catch (Exception e) {
                // Log.d(SyncStateContract.Constants.TAG, "Show Dialog: " + e.getMessage());
            }
        }
    }
    public boolean isOnline() {
        ConnectivityManager conMgr = (ConnectivityManager) getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = conMgr.getActiveNetworkInfo();

        if(netInfo == null || !netInfo.isConnected() || !netInfo.isAvailable()){
            return false;
        }
        return true;
    }
}