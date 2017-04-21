package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.ItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy


class ItemListFragment : PagerListFragment<ItemListFragmentViewModel, Item>() {

    override fun onCreateViewModel(context: Context): ItemListFragmentViewModel {
        return ItemListFragmentViewModel(context)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(context, viewModel.list.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                application.startScreen(ItemDetailScreen(item))
            }
        }
    }
}