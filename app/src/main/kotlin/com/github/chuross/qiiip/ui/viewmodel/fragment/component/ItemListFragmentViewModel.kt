package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel

class ItemListFragmentViewModel(context: Context) : PagerListFragmentViewModel<Item>(context) {

    fun fetch() = fetch(application.itemRepository.findAll(defaultPage, Settings.app.perPage))

    fun fetchNext() = fetchNext(application.itemRepository.findAll(nextPage, Settings.app.perPage))
}