package com.example.rxjavatestingfeatures.binding

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("visibleGone")
fun visibleGone(view: View, show: Boolean) {
    view.visibility = if (show) View.VISIBLE else View.GONE
}

@BindingAdapter("movieImage")
fun bindMovieImageView(imageView: ImageView, path: String) {
    val url = "https://image.tmdb.org/t/p/original$path"
    Glide.with(imageView.context).load(url).into(imageView)
}
