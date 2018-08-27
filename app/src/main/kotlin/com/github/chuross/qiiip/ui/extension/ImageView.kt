package com.github.chuross.qiiip.ui.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso
import jp.wasabeef.picasso.transformations.CropCircleTransformation

@BindingAdapter(value = ["imageUrl", "imageType"], requireAll = false)
fun ImageView.loadImage(imageUrl: String?, imageType: String?) {
    if (imageUrl == null) return

    Picasso.with(context).load(imageUrl).also { request ->
        request.fit()
        imageType?.let {
            when (it) {
                "circle" -> request.transform(CropCircleTransformation())
                else -> Unit
            }
        }
    }.into(this)
}