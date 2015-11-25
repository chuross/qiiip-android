package com.github.chuross.qiiip.ui.fragment.common

import android.support.v7.widget.RecyclerView
import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.ui.widget.MessageView

interface RequestTemplate : Template {

    val listView: RecyclerView

    val messageView: MessageView
}