package com.example.galleryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recycler_images;
    private List<String> paths = new ArrayList<>();
    private final int PERMISSION_REQCODE = 101;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recycler_images = findViewById(R.id.recycler_images);
        recycler_images.setHasFixedSize(true);
        recycler_images.setLayoutManager(new GridLayoutManager(this, 3));
//        recycler_images.setLayoutManager(new StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL));

        ImageAdapter adapter = new ImageAdapter(this, paths);
        recycler_images.setAdapter(adapter);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, PERMISSION_REQCODE);
        } else {
            appSetUP();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        // パーミッションが許可された時の処理
        if(requestCode == PERMISSION_REQCODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            appSetUP();
        }
    }

    @SuppressLint("Range")
    private void appSetUP() {
        Cursor cursor = getApplicationContext().getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            paths.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
        }

        ImageAdapter adapter = new ImageAdapter(this, paths);
        recycler_images.setAdapter(adapter);
    }
}