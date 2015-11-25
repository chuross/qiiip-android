package com.github.chuross.qiiip.ui.widget

import android.content.Context
import android.support.v4.app.FragmentManager
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout

class MessageView : FrameLayout {

    var retryCallback: (view: View) -> Unit? = {}

    var fragmentManager: FragmentManager? = null

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    fun showErrorMessage(error: Throwable) {
    }
}