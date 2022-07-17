package com.example.galleryapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.R;

public class VideoViewHolder extends RecyclerView.ViewHolder {
    public ImageView image_item;
    public CardView video_card;
    public VideoViewHolder(@NonNull View itemView) {
        super(itemView);
        image_item = itemView.findViewById(R.id.image_item);
        video_card = itemView.findViewById(R.id.image_card);
    }
}
