package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.RecentUpdatedItemListFragment
import com.github.chuross.qiiip.ui.fragment.screen.HomeScreenFragment

class HomeScreenFragmentPresenter(fragment: HomeScreenFragment) : SupportFragmentPresenter<HomeScreenFragment>(fragment) {

    val pagerItems: List<Pair<String, () -> Fragment>> = listOf(
            Pair("すべての記事", { RecentUpdatedItemListFragment() })
    )

}