package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import android.content.Context
import android.support.v4.app.Fragment
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.ui.view.fragment.component.FeedListFragment
import com.github.chuross.qiiip.ui.view.fragment.component.ItemListFragment
import com.github.chuross.qiiip.ui.view.fragment.component.StockItemListFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.FragmentViewModel

class HomeScreenFragmentViewModel(context: Context) : FragmentViewModel(context) {

    val title: String get() = context.getString(R.string.app_name)
    val defaultTabIndex: Int get() = if (application.isAuthorized) 1 else 0
    val tabItems: List<Pair<String, (() -> Fragment)>> get() =  if (application.isAuthorized) {
        listOf(
                Pair("全ての投稿", { ItemListFragment() }),
                Pair("フィード", { FeedListFragment() }),
                Pair("ストック", { StockItemListFragment() })
        )
    } else {
        listOf(
                Pair("全ての投稿", { ItemListFragment() })
        )
    }
}