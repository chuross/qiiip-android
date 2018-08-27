package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import com.github.chuross.morirouter.MoriBinder
import com.github.chuross.morirouter.annotation.Argument
import com.github.chuross.morirouter.annotation.WithArguments
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.UserItemListFragmentViewModel
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.UserItemListFragmentViewModelBuilder
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy

@WithArguments
class UserItemListFragment : PagerListFragment<UserItemListFragmentViewModel, Item>() {

    @Argument
    lateinit var user: User

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateViewModel(): UserItemListFragmentViewModel {
        return UserItemListFragmentViewModelBuilder(requireContext(), user).build(this)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(requireContext(), viewModel.list.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                screenActivity.router.itemDetail(item).launch()
            }
        }
    }
}