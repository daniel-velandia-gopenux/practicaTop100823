package com.example.top;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.top.adapter.ArtistAdapter;
import com.example.top.adapter.OnItemClickListener;
import com.example.top.databinding.ActivityMainBinding;
import com.example.top.model.Artist;
import com.example.top.model.ArtistsProvider;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {

    private ActivityMainBinding binding;
    private ArtistAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        initUI();
    }

    private void initUI() {
        configAdapter();
        configRecyclerView();
    }

    private void configAdapter() {
        this.adapter = new ArtistAdapter(ArtistsProvider.getArtists(), this);
    }

    private void configRecyclerView() {
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);
    }

    @Override
    public void onItemClick(Artist artist) {

    }

    @Override
    public void onLongItemClick(Artist artist) {

    }
}