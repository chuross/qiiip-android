package com.github.chuross.qiiip.ui.widget.template

import android.content.Context
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import kotlinx.android.synthetic.template_widget_retry_message_state.view.txt_message

class RetryMessageStateTemplate(context: Context, parent: ViewGroup) : AbstractTemplate(context, R.layout.template_widget_retry_message_state, parent) {

    val messageText by lazy { view.txt_message }

}