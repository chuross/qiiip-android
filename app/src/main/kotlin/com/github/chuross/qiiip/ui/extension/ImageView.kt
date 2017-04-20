package com.github.chuross.qiiip.ui.extension

import android.databinding.BindingAdapter
import android.widget.ImageView
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter(value = *arrayOf("imageUrl", "imageType"), requireAll = false)
fun ImageView.loadImage(imageUrl: String?, imageType: String?) {
    Picasso.with(context).load(imageUrl).apply {
        fit()
        imageType?.let {
            when (it) {
                "circle" -> transform(CropCircleTransformation())
                else -> Unit
            }
        }
    }.into(this)
}