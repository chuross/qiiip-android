package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentListBinding
import com.github.chuross.qiiip.ui.adapter.LoadingMoreViewItem
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import com.github.chuross.recyclerviewadapters.CompositeRecyclerAdapter
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

abstract class PagerListFragment<VM: PagerListFragmentViewModel<ITEM>, ITEM> : BaseFragment<FragmentListBinding, VM>() {

    override val layoutResourceId: Int = R.layout.fragment_list
    lateinit var itemAdapter: BaseItemAdapter<ITEM, *>

    abstract fun onCreateItemAdapter(): BaseItemAdapter<ITEM, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.fetch()
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CompositeRecyclerAdapter()

        itemAdapter = onCreateItemAdapter()

        val loadingAdapter = LoadingMoreViewItem(context, View.OnClickListener{
            viewModel.fetchNext()
        })
        loadingAdapter.isVisible = false

        adapter.add(itemAdapter)
        adapter.add(loadingAdapter)

        binding.viewModel = viewModel
        binding.swipeRefreshLayout?.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { viewModel.fetch() }
        }
        binding.list?.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }
        binding.status.retryListener = { viewModel.fetch() }

        viewModel.isLoading
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe {
                    if (it) binding.status.showLoadingView() else binding.status.hideAll()
                }.apply { viewModel.disposables.add(this) }

        viewModel.list
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe {
                    binding.status.hideAll()
                    loadingAdapter.isVisible = !it.isEmpty()
                }.apply { viewModel.disposables.add(this) }

        viewModel.fail
                .filter { viewModel.currentPageValue == viewModel.defaultPage }
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe { binding.status.showErrorView(it) }
                .apply { viewModel.disposables.add(this) }
    }
}