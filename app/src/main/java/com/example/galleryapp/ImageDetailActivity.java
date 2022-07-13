package com.example.galleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ImageDetailActivity extends AppCompatActivity {
    private String imagePath;
    private ImageView img_large;
    private ImageButton imgBtn_edit, imgBtn_share;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_detail);

        img_large = findViewById(R.id.img_large);
        imgBtn_edit = findViewById(R.id.imgBtn_edit);
        imgBtn_share = findViewById(R.id.imgBtn_share);

        Intent intent = getIntent();
        imagePath = intent.getStringExtra("path");
        img_large.setImageURI(Uri.parse(imagePath));

        imgBtn_share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent shareIntent = new Intent(Intent.ACTION_SEND);
                shareIntent.setType("image/*");
                shareIntent.putExtra(Intent.EXTRA_STREAM, imagePath);
                startActivity(Intent.createChooser(shareIntent, "Share screen"));
            }
        });
    }
}