package com.github.chuross.qiiip.ui.extension

import android.view.View
import androidx.core.view.ViewCompat
import androidx.databinding.BindingAdapter

@BindingAdapter(value = ["ext_transitionName"])
fun View.extSetTransitionName(transitionName: String?) {
    transitionName?.let { ViewCompat.setTransitionName(this, it) }
}