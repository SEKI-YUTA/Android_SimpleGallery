package com.example.galleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;

public class ImageDetailActivity extends AppCompatActivity {
    private String imagePath;
    private ImageView img_large;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        img_large = findViewById(R.id.img_large);

        Intent intent = getIntent();
        imagePath = intent.getStringExtra("path");

        img_large.setImageURI(Uri.parse(imagePath));
    }
}