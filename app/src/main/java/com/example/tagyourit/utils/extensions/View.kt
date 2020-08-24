package com.example.tagyourit.utils.extensions

import android.view.View
import android.widget.ImageView
import com.example.tagyourit.R
import com.squareup.picasso.Picasso

fun ImageView.loadUrl(url: String?) {
    Picasso.with(context)
        .load(url)
        .placeholder(R.drawable.ic_launcher_foreground)
        .error(R.drawable.ic_launcher_foreground)
        .into(this)
}

fun View.show(show: Boolean? = true) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}