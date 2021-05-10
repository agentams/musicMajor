package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;

public class
OfflineMusic extends AppCompatActivity {

    ListView listView;

    String[] items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_offline_music);
        listView = findViewById(R.id.listView);
        runtimePermission();

    }


    public void runtimePermission()
    {
        Dexter.withContext(this).withPermission(Manifest.permission.READ_EXTERNAL_STORAGE).withListener(new PermissionListener() {
            @Override
            public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                DisplaySongList();
            }

            @Override
            public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

            }

            @Override
            public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                permissionToken.continuePermissionRequest();
            }
        }).check();
    }
    public ArrayList<File> findSong(File file)
    {
        ArrayList<File> arrayList = new ArrayList<File>();
        File[] files = file.listFiles();
        for(File singlefile : files)
        {
           if(singlefile.isDirectory() && !singlefile.isHidden())
           {
               arrayList.addAll(findSong(singlefile));
           }
           else
               {
                   if(singlefile.getName().endsWith(".mp3") || singlefile.getName().endsWith(".wav")|| singlefile.getName().endsWith(".m4a"))
                   {
                       arrayList.add(singlefile);
                   }
                }
        }
        return arrayList;
    }
    void DisplaySongList()
    {
        final ArrayList<File> mySongs =findSong(Environment.getExternalStorageDirectory());

        items= new String[mySongs.size()];
        for(int i=0;i<mySongs.size();i++)
        {
         items[i]=mySongs.get(i).getName().toString()/*.replace(".mp3","").replace(".wav","").replace(".m4a","")*/;

        }
        ArrayAdapter<String> myAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,items);
        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String songname = listView.getItemAtPosition(position).toString();

                startActivity(new Intent(getApplicationContext(),PlayyerActivity.class).putExtra("songs",mySongs).putExtra("songname",songname).putExtra("position",position));
            }
        });
    }

}