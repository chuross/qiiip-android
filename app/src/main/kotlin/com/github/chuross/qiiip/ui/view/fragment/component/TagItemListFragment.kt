package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import android.os.Bundle
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.TagItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import io.reactivex.BackpressureStrategy

@FragmentWithArgs
class TagItemListFragment : PagerListFragment<TagItemListFragmentViewModel, Item>(){

    @Arg
    lateinit var tag: Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(context, viewModel.success.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                application.startScreen(ItemDetailScreen(item))
            }
        }
    }

    override fun onCreateViewModel(context: Context): TagItemListFragmentViewModel {
        return TagItemListFragmentViewModel(context, tag)
    }
}