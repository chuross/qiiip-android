package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.StockItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy

class StockItemListFragment : PagerListFragment<StockItemListFragmentViewModel, Item>() {

    override fun onCreateViewModel(context: Context): StockItemListFragmentViewModel {
        return StockItemListFragmentViewModel(context)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(requireContext(), viewModel.list.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                screenActivity.router.itemDetail(item)
            }
        }
    }
}