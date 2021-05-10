package com.example.musicmajor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class YoutubeMusic extends AppCompatActivity {
    private WebView webView;
    private ProgressBar ProBar;
    private SwipeRefreshLayout SwipeRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_youtube_music);

        if (isOnline()) {


            webView = findViewById(R.id.ytwebview);
            ProBar = findViewById(R.id.Probar);
            SwipeRef = findViewById(R.id.SwipeRefresh);


            WebSettings webSettings= webView.getSettings();
            webSettings.setJavaScriptEnabled(true);
            webSettings.setDomStorageEnabled(true);
            webView.getSettings().setLoadWithOverviewMode(true);
            webView.getSettings().setUseWideViewPort(true);

            webView.setWebViewClient(new myWebViewClient());
            webView.setWebViewClient(new myWebViewClient());
            webView.loadUrl("https://music.youtube.com");


            SwipeRef.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    webView.reload();
                }
            });
        }
        else {
            try {
                new AlertDialog.Builder(YoutubeMusic.this)
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
    @Override
    public void onBackPressed(){

        if(webView.canGoBack())
        {
            webView.goBack();
        }
        else{
            finish();
        }
    }

    private class myWebViewClient extends WebViewClient {
        @Override
        public void onPageFinished(WebView view, String url) {
            ProBar.setVisibility(View.GONE);
            SwipeRef.setRefreshing(false);
            super.onPageFinished(view, url);
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