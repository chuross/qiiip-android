package com.github.chuross.qiiip.ui.adapter

import android.content.Context
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.recyclerviewadapters.ViewItem

class LoadingMoreViewItem : ViewItem {

    constructor(context: Context) : super(context, R.layout.list_loading_more)

    constructor(context: Context, clickListener: View.OnClickListener) : super(context, R.layout.list_loading_more, clickListener)
}