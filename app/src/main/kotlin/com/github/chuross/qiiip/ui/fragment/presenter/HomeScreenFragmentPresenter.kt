package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.RecentUpdatedItemListFragment
import com.github.chuross.qiiip.ui.fragment.screen.HomeScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.HomeScreenFragmentTemplate

class HomeScreenFragmentPresenter(fragment: HomeScreenFragment) : SupportFragmentPresenter<HomeScreenFragment, HomeScreenFragmentTemplate>(fragment) {

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): HomeScreenFragmentTemplate = HomeScreenFragmentTemplate(view.activity)

    val pagerItems: List<Pair<String, () -> Fragment>> = listOf(
            Pair("すべての記事", { RecentUpdatedItemListFragment() })
    )

}