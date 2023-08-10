package com.example.top.adapter;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.top.R;
import com.example.top.model.Artist;
import com.google.android.material.imageview.ShapeableImageView;

public class ArtistViewHolder extends RecyclerView.ViewHolder {

    private View view;
    private TextView fullname;
    private TextView order;
    private ShapeableImageView imageView;

    public ArtistViewHolder(@NonNull View view) {
        super(view);
        this.view = view;
        this.imageView = (ShapeableImageView) view.findViewById(R.id.ivPhoto);
        this.fullname = (TextView) view.findViewById(R.id.tvFullName);
        this.order = (TextView) view.findViewById(R.id.tvOrder);
    }

    public void render(Artist artist) {

        Glide.with(this.view.getContext()).load("https://loremflickr.com/cache/resized/65535_52164091093_d701c8d93d_n_320_240_nofilter.jpg").into(this.imageView);
        this.fullname.setText(artist.getFullName());
        this.order.setText(String.valueOf(artist.getOrder()));
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
