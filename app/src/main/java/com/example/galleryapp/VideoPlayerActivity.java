package com.example.galleryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;
import android.widget.VideoView;

public class VideoPlayerActivity extends AppCompatActivity {
    private String videoPath;
    private VideoView videoView;
    private ImageButton imgBtn_play_pause;
    private boolean isPlaying = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_player);

        getSupportActionBar().hide();

        Intent intent = getIntent();
        videoPath = intent.getStringExtra("videoPath");
        imgBtn_play_pause = findViewById(R.id.imgBtn_play_pause);

        videoView = findViewById(R.id.videoView);
        if(videoPath != null) {
            videoView.setVideoURI(Uri.parse(videoPath));
        } else {
            Toast.makeText(this, "Something wrong", Toast.LENGTH_SHORT).show();
        }

        videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                Log.d("onCompletion", "called!");
                videoView.pause();
                isPlaying = false;
                imgBtn_play_pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
            }
        });

        imgBtn_play_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isPlaying) {
                    videoView.pause();
                    imgBtn_play_pause.setImageResource(R.drawable.ic_baseline_play_arrow_24);
                } else {
                    videoView.start();
                    imgBtn_play_pause.setImageResource(R.drawable.ic_baseline_pause_24);
                }
                isPlaying = !isPlaying;
            }
        });
    }


}