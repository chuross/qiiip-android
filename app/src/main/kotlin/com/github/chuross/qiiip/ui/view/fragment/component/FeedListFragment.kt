package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.FeedListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy

class FeedListFragment : PagerListFragment<FeedListFragmentViewModel, Item>() {

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(context, viewModel.success.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                application.startScreen(ItemDetailScreen(item))
            }
        }
    }

    override fun onCreateViewModel(context: Context): FeedListFragmentViewModel {
        return FeedListFragmentViewModel(context)
    }
}