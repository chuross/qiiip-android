package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel

class TagItemListFragmentViewModel(context: Context, val tag: Tag) : PagerListFragmentViewModel<Item>(context) {

    fun fetch() = fetch(application.itemRepository.findAllByTagIdentity(tag.identity, defaultPage, Settings.app.perPage))

    fun fetchNext() = fetch(application.itemRepository.findAllByTagIdentity(tag.identity, nextPage, Settings.app.perPage))
}