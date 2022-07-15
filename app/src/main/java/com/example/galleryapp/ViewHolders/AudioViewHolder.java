package com.example.galleryapp.ViewHolders;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.R;

public class AudioViewHolder extends RecyclerView.ViewHolder {
    public TextView audio_name;
    public ImageButton imgBtn_more;
    public AudioViewHolder(@NonNull View itemView) {
        super(itemView);
        audio_name = itemView.findViewById(R.id.audio_name);
        imgBtn_more = itemView.findViewById(R.id.imgBtn_more);
    }
}
