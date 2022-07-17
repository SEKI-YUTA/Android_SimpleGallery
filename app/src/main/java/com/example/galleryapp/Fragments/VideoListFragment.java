package com.example.galleryapp.Fragments;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galleryapp.Adapters.VideoAdapter;
import com.example.galleryapp.MyDialogFragment;
import com.example.galleryapp.R;

import java.util.ArrayList;
import java.util.List;

public class VideoListFragment extends Fragment {
    private RecyclerView recycler_videos;
    private List<String> paths = new ArrayList<>();
    private VideoAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_video_list, container, false);
        recycler_videos = view.findViewById(R.id.recycler_videos);
        recycler_videos.setHasFixedSize(true);
        recycler_videos.setLayoutManager(new GridLayoutManager(getContext(), 3));

        adapter = new VideoAdapter(getContext(), paths);

        recycler_videos.setAdapter(adapter);
        appSetUP();
        return view;
    }

    @SuppressLint("Range")
    private void appSetUP() {
        Cursor cursor = getContext().getApplicationContext().getContentResolver()
                .query(
                        MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

        while(cursor.moveToNext()) {
            paths.add(cursor.getString(cursor.getColumnIndex(MediaStore.Video.Media.DATA)));
        }

        VideoAdapter adapter = new VideoAdapter(getContext(), paths);
        recycler_videos.setAdapter(adapter);


    }

}