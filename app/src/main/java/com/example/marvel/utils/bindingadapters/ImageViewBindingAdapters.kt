package com.example.marvel.utils.bindingadapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.marvel.utils.extentions.loadImage

@BindingAdapter("bind:imageUrl")
fun ImageView.imageFromUrl(imageUrl: String?) {
    imageUrl?.let { loadImage(imageUrl) }
}
