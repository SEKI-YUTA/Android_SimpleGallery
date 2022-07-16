package com.example.galleryapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.ImageDetailActivity;
import com.example.galleryapp.R;
import com.example.galleryapp.ViewHolders.ImageViewHolder;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageViewHolder> {
    private Context context;
    private List<String> paths;

    public ImageAdapter(Context context, List<String> paths) {
        this.context = context;
        this.paths = paths;
    }

    @NonNull
    @Override
    public ImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ImageViewHolder(LayoutInflater.from(context).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ImageViewHolder holder, int position) {
        String path = paths.get(holder.getAdapterPosition());
        BitmapFactory.Options bmOptions = new BitmapFactory.Options();
        Bitmap bitmap = BitmapFactory.decodeFile(path, bmOptions);
        Bitmap resized = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
        holder.image_item.setImageBitmap(resized);
//        holder.image_item.setImageURI(Uri.parse(path));
//        Picasso.get().load(new File(path)).into(holder.image_item);
        holder.image_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ImageDetailActivity.class);
                intent.putExtra("path", path);
                context.startActivity(intent);
                ((Activity) context).overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
            }
        });
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }

}
