package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate
import com.github.chuross.qiiip.ui.widget.MessageView
import kotlinx.android.synthetic.template_list.view.list
import kotlinx.android.synthetic.template_list.view.status

class ItemListTemplate : NonApplyTemplate, ListTemplate {

    constructor(context: Context) : super(context, R.layout.template_list)

    override val list: RecyclerView by lazy { view.list }

    override val messageView: MessageView by lazy { view.status }

}