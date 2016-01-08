package com.github.chuross.qiiip.ui.widget.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.qiiip.ui.widget.CounterTextView
import kotlinx.android.synthetic.template_widget_countet_text_view.view.txt_count
import kotlinx.android.synthetic.template_widget_countet_text_view.view.txt_title

class CounterTextViewTemplate(context: Context, parent: CounterTextView) : AbstractTemplate(context, R.layout.template_widget_countet_text_view, parent) {

    val titleText = view.txt_title
    val countText = view.txt_count

}