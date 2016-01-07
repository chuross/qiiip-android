package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.AbstractTemplate
import kotlinx.android.synthetic.template_fragment_list.view.layout_swipe_refresh
import kotlinx.android.synthetic.template_fragment_list.view.list
import kotlinx.android.synthetic.template_fragment_list.view.status

class ListFragmentTemplate : AbstractTemplate, ListTemplate {

    constructor(context: Context) : super(context, R.layout.template_fragment_list)

    override val swipeRefreshLayout = view.layout_swipe_refresh
    override val list = view.list
    override val messageView = view.status

}