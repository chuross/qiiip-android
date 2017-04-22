package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.qiiip.usecase.RxUseCase

class UserItemListFragmentViewModel(context: Context, val user: User) : PagerListFragmentViewModel<Item>(context) {

    override fun useCase(): RxUseCase<List<Item>> {
        return application.useCases.getUserItems(user.identity, currentPageValue, Settings.app.perPage)
    }
}