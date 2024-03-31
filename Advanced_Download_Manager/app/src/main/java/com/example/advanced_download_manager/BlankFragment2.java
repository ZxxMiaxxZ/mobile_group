package com.example.advanced_download_manager;

import android.app.DownloadManager;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class BlankFragment2 extends Fragment {
    private ListView mListView;
    private ArrayAdapter<String> mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_blank2, container, false);

        mListView = rootView.findViewById(R.id.list_view);

        List<String> downloadedFilesList = getListOfDownloadedFiles();
        mAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, downloadedFilesList);
        mListView.setAdapter(mAdapter);

        return rootView;
    }

    private List<String> getListOfDownloadedFiles() {
        List<String> downloadedFilesList = new ArrayList<>();

        // Query the DownloadManager to get the list of downloaded files
        DownloadManager downloadManager = (DownloadManager) getActivity().getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Query query = new DownloadManager.Query();
        query.setFilterByStatus(DownloadManager.STATUS_SUCCESSFUL);
        Cursor cursor = downloadManager.query(query);

        if (cursor != null) {
            int columnIndex = cursor.getColumnIndex(DownloadManager.COLUMN_TITLE);
            if (columnIndex != -1) { // Check if the column exists
                while (cursor.moveToNext()) {
                    String fileName = cursor.getString(columnIndex);
                    downloadedFilesList.add(fileName);
                }
            }
            cursor.close();
        }

        return downloadedFilesList;
    }
}
