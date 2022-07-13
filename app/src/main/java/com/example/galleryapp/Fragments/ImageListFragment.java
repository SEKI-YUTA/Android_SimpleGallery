package com.example.galleryapp.Fragments;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.galleryapp.ImageAdapter;
import com.example.galleryapp.R;

import java.util.ArrayList;
import java.util.List;

public class ImageListFragment extends Fragment {
    private RecyclerView recycler_images;
    private List<String> paths = new ArrayList<>();
    private ImageAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("ImageListFragment" , "onCreateView");
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_image_list, container, false);
        recycler_images = view.findViewById(R.id.recycler_images);
        recycler_images.setHasFixedSize(true);
        recycler_images.setLayoutManager(new GridLayoutManager(getContext(), 3));
//        recycler_images.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        adapter = new ImageAdapter(getContext(), paths);
        recycler_images.setAdapter(adapter);

        appSetUP();
        return view;
    }


    @SuppressLint("Range")
    private void appSetUP() {
        Log.d("ImageListFragment" , "appSetUP");
        Cursor cursor = getContext().getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            paths.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
        }

        ImageAdapter adapter = new ImageAdapter(getContext(), paths);
        recycler_images.setAdapter(adapter);
    }



}