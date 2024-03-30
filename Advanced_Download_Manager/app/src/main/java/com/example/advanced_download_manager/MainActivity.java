package com.example.advanced_download_manager;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private static final int PERMISSION_STOGARE_CODE =1000;
    EditText mUrlEt;
    Button mDownloadBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mUrlEt=findViewById(R.id.urlEt);
        mDownloadBtn = findViewById(R.id.downloadUrl);

        //handle button
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              startDownloading();
            }
        });

    }
    private void startDownloading()
    {
        String url = mUrlEt.getText().toString().trim();
        //create download request
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(url));
        //allow types of network to download
        request.setAllowedNetworkTypes(DownloadManager.Request.NETWORK_WIFI | DownloadManager.Request.NETWORK_MOBILE);
        request.setTitle("Download");
        request.setDescription("Downloading file....");
        request.allowScanningByMediaScanner();
        request.setNotificationVisibility((DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED));
        request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,""+System.currentTimeMillis());

        //get download service and enque file
        DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);

    }


}