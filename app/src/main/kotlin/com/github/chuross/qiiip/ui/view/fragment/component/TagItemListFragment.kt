package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.databinding.FragmentItemListBinding
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.adapter.LoadingMoreViewItem
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.TagItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.CompositeRecyclerAdapter
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.BackpressureStrategy

@FragmentWithArgs
class TagItemListFragment : BaseFragment<FragmentItemListBinding>(){

    override val layoutResourceId: Int = R.layout.fragment_item_list
    @Arg
    lateinit var tag: Tag
    private lateinit var viewModel: TagItemListFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        viewModel = TagItemListFragmentViewModel(context, tag)
        bindViewModel(viewModel)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CompositeRecyclerAdapter()

        val itemAdapter = ItemAdapter(context, viewModel.items.toFlowable(BackpressureStrategy.LATEST))
        itemAdapter.setOnItemClickListener { _, _, item ->
            application.startScreen(ItemDetailScreen(item))
        }

        val loadingAdapter = LoadingMoreViewItem(context, View.OnClickListener{
            viewModel.fetchNextItems()
        })

        adapter.add(itemAdapter)
        adapter.add(loadingAdapter)

        binding?.swipeRefreshLayout?.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { viewModel.fetchItems() }
        }
        binding?.list?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }

        viewModel.isLoading
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe({ binding?.swipeRefreshLayout?.isRefreshing = it })
                .apply { viewModel.disposables.add(this) }

        if (viewModel.items.get()?.isEmpty() ?: true) viewModel.fetchItems()
    }
}