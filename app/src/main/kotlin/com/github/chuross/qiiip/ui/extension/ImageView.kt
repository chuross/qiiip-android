package com.github.chuross.qiiip.ui.extension

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String) {
    Picasso.with(context).load(imageUrl).apply {
        fit()
        transform(CropCircleTransformation())
    }.into(this)
}