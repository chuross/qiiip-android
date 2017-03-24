package com.github.chuross.qiiip.ui.extension

import android.view.View
import android.view.ViewTreeObserver
import me.henrytao.smoothappbarlayout.SmoothAppBarLayout

fun SmoothAppBarLayout.bindPaddingView(view: View?) {
    view?.let {
        viewTreeObserver?.let {
        it.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                it.removeOnGlobalLayoutListener(this)
                view.let {
                    it.layoutParams.height = height
                    it.requestLayout()
                }
            }
        })
    }
    }
}