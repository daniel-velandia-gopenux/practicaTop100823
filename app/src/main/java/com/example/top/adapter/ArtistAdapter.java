package com.example.top.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.top.R;
import com.example.top.model.Artist;

import java.util.List;

public class ArtistAdapter extends RecyclerView.Adapter<ArtistViewHolder> {

    private List<Artist> artists;
    private OnItemClickListener listener;

    public ArtistAdapter(List<Artist> artists, OnItemClickListener listener) {
        this.artists = artists;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ArtistViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent,
                false);

        return new ArtistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ArtistViewHolder holder, int position) {
        Artist artist = this.artists.get(position);
        holder.render(artist);
        holder.setListeners(artist, listener);
    }

    @Override
    public int getItemCount() {return this.artists.size();}

    public void addArtist(Artist artist) {
        if(!artists.contains(artist)) {
            this.artists.add(artist);
            this.notifyDataSetChanged();
        }
    }
}
