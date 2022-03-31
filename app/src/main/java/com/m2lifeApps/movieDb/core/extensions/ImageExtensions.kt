package com.m2lifeApps.movieDb.core.extensions

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.loadAny
import com.makeramen.roundedimageview.RoundedImageView

@BindingAdapter("app:imageUrl")
fun AppCompatImageView.loadImage(url: Any?) {
    this.loadAny(url)
}

@BindingAdapter("app:imageUrl")
fun RoundedImageView.loadImage(url: Any?) {
    this.loadAny(url)
}

@BindingAdapter("app:imageUrlPost")
fun AppCompatImageView.loadImagePost(url: Any?) {
    this.loadAny("https://image.tmdb.org/t/p/w500$url")
}

@BindingAdapter("app:imageUrlPost")
fun RoundedImageView.loadImagePost(url: Any?) {
    this.loadAny("https://image.tmdb.org/t/p/w500$url")
}
