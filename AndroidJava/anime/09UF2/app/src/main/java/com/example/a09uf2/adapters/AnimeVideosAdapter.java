package com.example.a09uf2.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.a09uf2.R;
import com.example.a09uf2.VideoActivity;
import com.example.a09uf2.model.AnimeVideos;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AnimeVideosAdapter extends RecyclerView.Adapter<AnimeVideosAdapter.AnimeVideosViewHolder> {
    private List<AnimeVideos> animeVideos;

    public AnimeVideosAdapter(List<AnimeVideos> animeVideos) {
        this.animeVideos = animeVideos;
    }

    @NonNull
    @Override
    public AnimeVideosAdapter.AnimeVideosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.anime_videos_recycler, null, false);
        return new AnimeVideosAdapter.AnimeVideosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeVideosAdapter.AnimeVideosViewHolder holder, int position) {
        Picasso.get().load("https://joanseculi.com/"+animeVideos.get(position).getImage()).fit().centerCrop().into(holder.image);
        holder.text.setText(animeVideos.get(position).getEpisode());
    }

    @Override
    public int getItemCount() {
        return animeVideos.size();
    }

    public class AnimeVideosViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;

        public AnimeVideosViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageVideo);
            text = itemView.findViewById(R.id.textVideo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, VideoActivity.class);
                    intent.putExtra("url",animeVideos.get(getAdapterPosition()).getUrl());
                    context.startActivity(intent);
                }
            });

        }
    }
}
