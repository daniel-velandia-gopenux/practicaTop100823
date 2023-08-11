package com.example.top.utils;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.top.R;
import com.google.android.material.imageview.ShapeableImageView;

public class ImageViewUtils {

    public static void loadImage(String photo, ShapeableImageView imageView) {
        RequestOptions options = new RequestOptions();
        options.diskCacheStrategy(DiskCacheStrategy.ALL);
        options.centerCrop();
        options.placeholder(R.drawable.baseline_photo_size_select_actual_80);


        Glide
                .with(imageView.getContext())
                .load(photo)
                .apply(options)
                .into(imageView);
    }
}
