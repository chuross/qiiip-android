package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.qiiip.usecase.RxUseCase

class ItemListFragmentViewModel(context: Context) : PagerListFragmentViewModel<Item>(context) {

    override fun useCase(): RxUseCase<List<Item>> {
        return application.useCases.getItems(currentPageValue, Settings.app.perPage)
    }
}