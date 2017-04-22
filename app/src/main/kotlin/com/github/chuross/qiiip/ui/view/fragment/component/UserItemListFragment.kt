package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import android.os.Bundle
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.UserItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import io.reactivex.BackpressureStrategy

@FragmentWithArgs
class UserItemListFragment : PagerListFragment<UserItemListFragmentViewModel, Item>() {

    @Arg lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateViewModel(context: Context): UserItemListFragmentViewModel {
        return UserItemListFragmentViewModel(context, user)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(context, viewModel.list.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                application.startScreen(ItemDetailScreen(item))
            }
        }
    }
}