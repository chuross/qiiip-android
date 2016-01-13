package com.github.chuross.qiiip.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.ui.widget.template.RetryMessageStateTemplate

class MultiStateView : FrameLayout {

    var retryCallback: (view: View) -> Unit? = {}
    private val loadingView: View by lazy { LayoutInflater.from(context).inflate(R.layout.widget_state_loading, this, false) }
    private val retryMessageStateTemplate: RetryMessageStateTemplate by lazy { RetryMessageStateTemplate(context, this) }

    constructor(context: Context?) : super(context)

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes)

    private fun showTargetView(view: View) {
        if (!view.isAttachedToWindow) {
            addView(view, 0)
        } else {
            attachViewToParent(view, 0, view.layoutParams)
        }
        refresh()
    }

    private fun refresh() {
        requestLayout()
        invalidate()
    }

    private fun showMessageView(resourceId: Int) {
        detachAllViewsFromParent()
        showTargetView(retryMessageStateTemplate.view)

        retryMessageStateTemplate.messageText.text = resources.getString(resourceId)
    }

    fun showLoadingView() {
        detachAllViewsFromParent()
        showTargetView(loadingView)
    }

    fun showErrorView(throwable: Throwable) {
        showMessageView(R.string.error_default)
    }

    fun showEmptyView() {
        showMessageView(R.string.error_empty)
    }

    fun showDisconnectedView() {
        showMessageView(R.string.error_disconnected)
    }

    fun hideAll() {
        detachAllViewsFromParent()
        refresh()
    }

}