package com.example.advanced_download_manager;

import android.app.DownloadManager;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class BlankFragment1 extends Fragment {
    private static final int PERMISSION_STOGARE_CODE =1000;


    private EditText mUrlEt;
    private Button mDownloadBtn;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank1, container, false);

        mUrlEt = rootView.findViewById(R.id.urlEt);
        mDownloadBtn = rootView.findViewById(R.id.downloadUrl);

        //handle button click
        mDownloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startDownloading();
            }
        });

        return rootView;
    }

    private void startDownloading() {
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

        //get download service and enqueue file
        DownloadManager manager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        manager.enqueue(request);
    }
}
