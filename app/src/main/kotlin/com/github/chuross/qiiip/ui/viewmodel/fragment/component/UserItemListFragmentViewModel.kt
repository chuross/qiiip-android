package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.qiiip.usecase.RxUseCase
import com.github.chuross.viewmodelargs.annotation.Argument
import com.github.chuross.viewmodelargs.annotation.ViewModelWithArgs

@ViewModelWithArgs
class UserItemListFragmentViewModel : PagerListFragmentViewModel<Item>() {

    @Argument
    override lateinit var context: Context

    @Argument
    lateinit var user: User

    override fun useCase(): RxUseCase<List<Item>> {
        return qiiipApplication.useCases.getUserItems(user.identity, currentPageValue, Settings.app.perPage)
    }
}