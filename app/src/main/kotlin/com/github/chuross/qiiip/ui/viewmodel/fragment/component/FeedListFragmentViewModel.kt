package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel

class FeedListFragmentViewModel(context: Context) : PagerListFragmentViewModel<Item>(context) {

    fun fetch() = fetch(application.useCases.getFeeds(defaultPage, Settings.app.perPage).exec())

    fun fetchNext() = fetchNext(application.useCases.getFeeds(nextPage, Settings.app.perPage).exec())
}