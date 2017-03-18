package com.github.chuross.qiiip.ui.viewmodel.fragment

import android.content.Context
import android.support.v4.app.Fragment
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.ui.view.fragment.component.ItemListFragment

class HomeScreenFragmentViewModel(context: Context) : FragmentViewModel(context) {

    val title: String get() = context.getString(R.string.app_name)
    val tabItems: List<Pair<String, (() -> Fragment)>> = listOf(
            Pair("全ての記事", { ItemListFragment() })
    )
}