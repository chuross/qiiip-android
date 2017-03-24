package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel

class ItemListFragmentViewModel(context: Context) : PagerListFragmentViewModel<Item>(context) {

    private val itemRepository get() = application.repositories.itemRepository

    override fun fetch() = fetch(itemRepository.findAll(defaultPage, Settings.app.perPage))

    override fun fetchNext() = fetchNext(itemRepository.findAll(nextPage, Settings.app.perPage))
}