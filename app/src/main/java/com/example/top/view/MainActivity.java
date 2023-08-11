package com.example.top.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.top.adapter.ArtistAdapter;
import com.example.top.adapter.OnItemClickListener;
import com.example.top.databinding.ActivityMainBinding;
import com.example.top.model.Artist;
import com.example.top.model.ArtistsProvider;

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
        onFlotaingButtonClick();
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

    private void onFlotaingButtonClick() {
        binding.faButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddArtistActivity.class);
                intent.putExtra(Artist.ORDER, adapter.getItemCount() - 1);
                startActivity(intent);
            }
        });
    }
}