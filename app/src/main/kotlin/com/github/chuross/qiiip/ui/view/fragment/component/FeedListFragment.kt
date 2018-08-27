package com.github.chuross.qiiip.ui.view.fragment.component

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.FeedListFragmentViewModel
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.FeedListFragmentViewModelBuilder
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy

class FeedListFragment : PagerListFragment<FeedListFragmentViewModel, Item>() {

    override fun onCreateViewModel(): FeedListFragmentViewModel {
        return FeedListFragmentViewModelBuilder(requireContext()).build(this)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(requireContext(), viewModel.list.toFlowable(BackpressureStrategy.LATEST)).also {
            it.setOnItemClickListener { _, _, item ->
                screenActivity.router.itemDetail(item).launch()
            }
        }
    }
}