package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.qiiip.usecase.RxUseCase

class TagItemListFragmentViewModel(context: Context, val tag: Tag) : PagerListFragmentViewModel<Item>(context) {

    override fun useCase(): RxUseCase<List<Item>> {
        return application.useCases.getItemsByTagId(tag.identity, currentPageValue, Settings.app.perPage)
    }
}