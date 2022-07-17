package com.example.galleryapp.Adapters;

import android.app.Activity;
import android.app.Dialog;
import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.MediaPlayer;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.VideoView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.galleryapp.MyDialogFragment;
import com.example.galleryapp.R;
import com.example.galleryapp.VideoPlayerActivity;
import com.example.galleryapp.ViewHolders.VideoViewHolder;

import java.util.List;

public class VideoAdapter extends RecyclerView.Adapter<VideoViewHolder> {
    private Context context;
    private List<String> paths;

    public VideoAdapter(Context context, List<String> paths) {
        this.context = context;
        this.paths = paths;
    }

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // リスト表示ではサムネイルを表示させるだけなので画像のレイアウトを再利用する
        return new VideoViewHolder(LayoutInflater.from(context).inflate(R.layout.image_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        String path = paths.get(holder.getAdapterPosition());
        Bitmap thumbnail = ThumbnailUtils.extractThumbnail(ThumbnailUtils.createVideoThumbnail(path, MediaStore.Video.Thumbnails.MINI_KIND), 300, 300);
        holder.image_item.setImageBitmap(thumbnail);
        holder.video_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, VideoPlayerActivity.class);
                intent.putExtra("videoPath", path);
                context.startActivity(intent);
            }
        });
        holder.video_card.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.video_dialog);
                VideoView videoView = dialog.findViewById(R.id.dialog_video);
                videoView.setVideoURI(Uri.parse(path));
                videoView.start();
                videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mediaPlayer) {
                        dialog.dismiss();
                    }
                });
                dialog.show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return paths.size();
    }
}
