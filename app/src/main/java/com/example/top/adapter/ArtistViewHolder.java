package com.example.top.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top.databinding.ItemArtistBinding;
import com.example.top.utils.ImageViewUtils;
import com.example.top.R;
import com.example.top.model.Artist;
import com.google.android.material.imageview.ShapeableImageView;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private ItemArtistBinding binding;

    public ArtistViewHolder(@NonNull ItemArtistBinding binding) {
        super(binding.getRoot());
        this.binding = binding;
    }

    public void render(Artist artist) {

        ImageViewUtils.loadImage(artist.getUrlPhoto(), binding.ivPhoto);
        binding.tvFullName.setText(artist.getFullName());
        binding.tvOrder.setText(String.valueOf(artist.getOrder()));
    }

    public void setListeners(Artist artist, OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onItemClick(artist);
            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                listener.onLongItemClick(artist);
                return true;
            }
        });
    }
}
