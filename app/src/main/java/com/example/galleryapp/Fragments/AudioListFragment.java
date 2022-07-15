package com.example.galleryapp.Fragments;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galleryapp.Adapters.AudioAdapter;
import com.example.galleryapp.R;

import java.util.ArrayList;
import java.util.List;

public class AudioListFragment extends Fragment {
    private RecyclerView recycler_audios;
    private List<String> paths = new ArrayList<>();
    private AudioAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_audio_list, container, false);

        recycler_audios = view.findViewById(R.id.recycler_audios);
        recycler_audios.setHasFixedSize(true);
        recycler_audios.setLayoutManager(new GridLayoutManager(getContext(), 1));

        adapter = new AudioAdapter(getContext(), paths);
        recycler_audios.setAdapter(adapter);

        appSetUP();
        return view;
    }

    @SuppressLint("Range")
    private void appSetUP() {
        Log.d("MusicListFragment", "appSetUP()");
        Cursor cursor = getContext().getApplicationContext().getContentResolver()
                .query(
                        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI,
                        null,
                        null,
                        null,
                        null
                );

        while(cursor.moveToNext()) {
            Log.d("cursor", cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
            paths.add(cursor.getString(cursor.getColumnIndex(MediaStore.Audio.Media.DATA)));
        }

        AudioAdapter adapter = new AudioAdapter(getContext(), paths);
        recycler_audios.setAdapter(adapter);
    }
}