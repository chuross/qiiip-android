package com.github.chuross.qiiip.ui.extension

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter("imageUrl")
fun ImageView.loadImage(imageUrl: String?) {
    imageUrl?.let {
        Picasso.with(context).load(it).apply {
            fit()
            transform(CropCircleTransformation())
        }.into(this)
    }
}