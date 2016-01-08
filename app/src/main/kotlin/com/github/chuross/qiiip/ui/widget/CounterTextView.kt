package com.github.chuross.qiiip.ui.widget

import android.content.Context
import android.util.AttributeSet
import android.widget.LinearLayout
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.ui.widget.template.CounterTextViewTemplate

class CounterTextView : LinearLayout {

    public var titleText: String
        get() = titleText
        set(value) {
            template.titleText.text = value
        }
    public var countText: String
        get() = countText
        set(value) {
            template.countText.text = value
        }

    private val template  by lazy { CounterTextViewTemplate(context, this) }

    constructor(context: Context?) : super(context) {
        init(null, null, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init(attrs, null, null)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(attrs, defStyleAttr, null)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : super(context, attrs, defStyleAttr, defStyleRes) {
        init(attrs, defStyleAttr, defStyleRes)
    }

    private fun init(attrs: AttributeSet?, defStyleAttr: Int?, defStyleRes: Int?) {
        addView(template.view)
        attrs?.let { attrs ->
            context.obtainStyledAttributes(attrs, R.styleable.CounterTextView, defStyleAttr ?: 0, defStyleRes ?: 0).apply {
                getString(R.styleable.CounterTextView_co_txt_titleText)?.let { template.titleText.text = it }
                getColor(R.styleable.CounterTextView_co_txt_titleTextColor, 0).let { if (it != 0) template.titleText.setTextColor(it) }
                getString(R.styleable.CounterTextView_co_txt_counterText)?.let { template.countText.text = it }
                getColor(R.styleable.CounterTextView_co_txt_counterTextColor, 0).let { if (it != 0) template.countText.setTextColor(it) }
            }.recycle();
        }
    }

}