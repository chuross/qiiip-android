package com.github.chuross.qiiip.ui.view.widget

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.ViewStatusErrorBinding
import retrofit2.HttpException

class StatusView : FrameLayout {

    var retryListener: (() -> Unit)? = null
    private lateinit var loadingView: View
    private lateinit var errorBinding: ViewStatusErrorBinding

    constructor(context: Context?) : super(context) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(context)
    }

    private fun init(context: Context?) {
        if (context == null) return

        loadingView = LayoutInflater.from(context).inflate(R.layout.view_status_loading, this, false)
        errorBinding = ViewStatusErrorBinding.inflate(LayoutInflater.from(context), this, false)
        errorBinding.retryBtn.setOnClickListener { retryListener?.invoke() }

        addView(loadingView)
        addView(errorBinding.root)
        hideAll()
    }

    fun showLoadingView() {
        hideAll()
        loadingView.visibility = View.VISIBLE
    }

    fun showErrorView(throwable: Throwable) {
        hideAll()
        errorBinding.root.visibility = View.VISIBLE
        when (throwable) {
            is HttpException -> showApiErrorView(throwable)
            else -> {
                errorBinding.messageTxt.text = resources.getString(R.string.error_default)
            }
        }
    }

    private fun showApiErrorView(exception: HttpException) {
        errorBinding.messageTxt.text = resources.getString(R.string.error_default)
    }

    fun hideAll() {
        loadingView.visibility = View.INVISIBLE
        errorBinding.root.visibility = View.INVISIBLE
    }
}