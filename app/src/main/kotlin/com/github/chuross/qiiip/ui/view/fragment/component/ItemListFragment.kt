package com.github.chuross.qiiip.ui.view.fragment.component

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.ItemListFragmentViewModel
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.ItemListFragmentViewModelBuilder
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy


class ItemListFragment : PagerListFragment<ItemListFragmentViewModel, Item>() {

    override fun onCreateViewModel(): ItemListFragmentViewModel {
        return ItemListFragmentViewModelBuilder(requireContext()).build(this)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(requireContext(), viewModel.list.toFlowable(BackpressureStrategy.LATEST)).also {
            it.setOnItemClickListener { _, _, item ->
                screenActivity.router.itemDetail(item).launch()
            }
        }
    }
}