package com.github.chuross.qiiip.ui.fragment.template

import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.RecyclerView

interface ListTemplate : RequestTemplate {

    val swipeRefreshLayout: SwipeRefreshLayout

    val list: RecyclerView
}