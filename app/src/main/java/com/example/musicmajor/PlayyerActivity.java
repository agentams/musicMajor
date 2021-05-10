package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.io.File;
import java.util.ArrayList;

public class PlayyerActivity extends AppCompatActivity {
    Button btn_next,btn_previous,btn_pause;
    TextView songTitle;
    SeekBar songSeekbar;

    static MediaPlayer mymediaplayer;
    int position;
    ArrayList<File> mysongs;
    Thread updateseekbar;
    String sname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_playyer);
        btn_next= (Button) findViewById(R.id.next);
        btn_pause= (Button) findViewById(R.id.pause);
        btn_previous= (Button) findViewById(R.id.previous);
        songTitle= (TextView) findViewById(R.id.textview);
        songSeekbar= (SeekBar) findViewById(R.id.seekBar);


        updateseekbar=new Thread()
        {

            @Override
            public  void run()
            {
                int totalDuration = mymediaplayer.getDuration();
                int currentPosition = 0;
                int adv = 0;
                while((adv = ((adv = totalDuration - currentPosition) < 500)?adv:500) > 2)
                {
                    try
                    {
                        sleep(500);
                        currentPosition=mymediaplayer.getCurrentPosition();
                        if (songSeekbar != null) {
                            songSeekbar.setProgress(currentPosition);
                        }
                        sleep(adv);

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }

                }
            }
        };


        if(mymediaplayer!=null)
        {
            mymediaplayer.stop();
            mymediaplayer.release();
        }
        Intent intent =getIntent();
        Bundle bundle=intent.getExtras();



        mysongs=(ArrayList) bundle.getParcelableArrayList("songs");
        sname=mysongs.get(position).getName().toString();
        String SongName= intent.getStringExtra("songname");
        songTitle.setText(SongName);
        songTitle.setSelected(true);


        position=bundle.getInt("position",0);

        Uri u = Uri.parse((mysongs.get(position).toString()));

        mymediaplayer= MediaPlayer.create(getApplicationContext(),u);
        mymediaplayer.start();
        songSeekbar.setMax(mymediaplayer.getDuration());
        updateseekbar.start();

        songSeekbar.setOnSeekBarChangeListener((new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar)
            {
                mymediaplayer.seekTo(seekBar.getProgress());

            }
        }));


        btn_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                songSeekbar.setMax(mymediaplayer.getDuration());
                if(mymediaplayer.isPlaying())
                {
                    btn_pause.setBackgroundResource(R.drawable.icon_play);
                    mymediaplayer.pause();
                }
                else {
                    btn_pause.setBackgroundResource(R.drawable.icon_pause);
                    mymediaplayer.start();
                }
            }
        });




        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mymediaplayer.stop();
                mymediaplayer.release();
                position=((position+1)%mysongs.size());
              Uri u = Uri.parse(mysongs.get(position).toString());
              mymediaplayer= MediaPlayer.create(getApplicationContext(),u);
              sname=mysongs.get(position).getName().toString();
              songTitle.setText(sname);
              mymediaplayer.start();
            }
        });
        btn_previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                mymediaplayer.stop();
                mymediaplayer.release();
                position=((position-1)<0)?(mysongs.size()-1):(position-1);
                Uri u = Uri.parse(mysongs.get(position).toString());
                mymediaplayer= MediaPlayer.create(getApplicationContext(),u);
                sname=mysongs.get(position).getName().toString();
                songTitle.setText(sname);
                mymediaplayer.start();
            }
        });


    }

}