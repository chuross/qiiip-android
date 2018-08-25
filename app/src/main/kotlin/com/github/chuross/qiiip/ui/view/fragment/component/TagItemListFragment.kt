package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
import android.os.Bundle
import com.github.chuross.morirouter.MoriBinder
import com.github.chuross.morirouter.annotation.Argument
import com.github.chuross.morirouter.annotation.WithArguments
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.TagItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import io.reactivex.BackpressureStrategy

@WithArguments
class TagItemListFragment : PagerListFragment<TagItemListFragmentViewModel, Item>() {

    @Argument
    lateinit var tag: Tag

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateViewModel(context: Context): TagItemListFragmentViewModel {
        return TagItemListFragmentViewModel(context, tag)
    }

    override fun onCreateItemAdapter(): BaseItemAdapter<Item, *> {
        return ItemAdapter(requireContext(), viewModel.list.toFlowable(BackpressureStrategy.LATEST)).apply {
            setOnItemClickListener { _, _, item ->
                screenActivity.router.itemDetail(item).launch()
            }
        }
    }
}