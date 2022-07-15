package com.example.galleryapp.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.R;
import com.example.galleryapp.ViewHolders.AudioViewHolder;

import java.util.List;

public class AudioAdapter extends RecyclerView.Adapter<AudioViewHolder> {
    private Context context;
    private List<String> names;

    public AudioAdapter(Context context, List<String> names) {
        this.context = context;
        this.names = names;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AudioViewHolder(LayoutInflater.from(context).inflate(R.layout.audio_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        String[] dirs = names.get(holder.getAdapterPosition()).split("/");
        String name = dirs[dirs.length - 1];
        holder.audio_name.setText(name);
        holder.imgBtn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, name, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return names.size();
    }
}
