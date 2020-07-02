package com.noticeboardapp.utils_classes;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;

public class ImageSetter {

    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String url) {
        if(url!=null && view!=null)
            Glide.with(view.getContext()).load(url).into(view);
    }
}
