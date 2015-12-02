package com.github.chuross.qiiip.ui.fragment.template

import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.ui.widget.MessageView

interface RequestTemplate : Template {

    val messageView: MessageView
}