package com.example.tagyourit.utils.extensions

import android.view.View
import android.widget.ImageView
import com.example.tagyourit.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?, width: Int, height: Int) {

    Picasso.with(context)
        .load(url)
        .resize(width, height)
        .centerCrop()
        .placeholder(R.drawable.pexels)
        .error(R.drawable.error)
        .into(this)
}

fun View.show(show: Boolean? = true) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}