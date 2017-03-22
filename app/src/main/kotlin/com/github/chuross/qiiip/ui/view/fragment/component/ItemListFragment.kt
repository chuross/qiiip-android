package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.screen.ItemDetailScreen
import com.github.chuross.qiiip.databinding.FragmentItemListBinding
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.adapter.LoadingMoreViewItem
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.component.ItemListFragmentViewModel
import com.github.chuross.recyclerviewadapters.CompositeRecyclerAdapter
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.BackpressureStrategy

class ItemListFragment : BaseFragment<FragmentItemListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_item_list
    private lateinit var viewModel: ItemListFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ItemListFragmentViewModel(context)
        bindViewModel(viewModel)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CompositeRecyclerAdapter()

        val itemAdapter = ItemAdapter(context, viewModel.fetchedResult.toFlowable(BackpressureStrategy.LATEST))
        itemAdapter.setOnItemClickListener { _, _, item ->
            application.startScreen(ItemDetailScreen(item))
        }

        val loadingAdapter = LoadingMoreViewItem(context, View.OnClickListener{
            viewModel.fetchNext()
        })
        loadingAdapter.isVisible = false

        adapter.add(itemAdapter)
        adapter.add(loadingAdapter)

        binding?.swipeRefreshLayout?.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { viewModel.fetch() }
        }
        binding?.list?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }

        viewModel.fetchedResult
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe {
                    binding?.status?.hideAll()
                    loadingAdapter.isVisible = !it.isEmpty()
                }.apply { viewModel.disposables.add(this) }

        viewModel.isLoading
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe({
                    binding?.swipeRefreshLayout?.isRefreshing = it
                    if (it) binding?.status?.showLoadingView()
                })
                .apply { viewModel.disposables.add(this) }

        viewModel.error
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe { it.second?.let { binding?.status?.showErrorView(it) } }
                .apply { viewModel.disposables.add(this) }

        if (viewModel.fetchedResult.get()?.isEmpty() ?: true) {
            viewModel.fetch()
        }
    }
}