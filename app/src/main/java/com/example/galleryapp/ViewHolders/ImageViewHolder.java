package com.example.galleryapp.ViewHolders;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.R;

public class ImageViewHolder extends RecyclerView.ViewHolder{
    public ImageView image_item;
    public CardView image_card;
    public ImageViewHolder(@NonNull View itemView) {
        super(itemView);
        image_item = itemView.findViewById(R.id.image_item);
        image_card = itemView.findViewById(R.id.image_card);
    }
}
