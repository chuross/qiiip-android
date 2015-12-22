package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import com.github.chuross.qiiip.ui.widget.MessageView
import kotlinx.android.synthetic.template_fragment_list.view.list
import kotlinx.android.synthetic.template_fragment_list.view.status

class ListFragmentTemplate : AbstractTemplate, ListTemplate {

    constructor(context: Context) : super(context, R.layout.template_fragment_list)

    override val list: RecyclerView = view.list

    override val messageView: MessageView = view.status

}