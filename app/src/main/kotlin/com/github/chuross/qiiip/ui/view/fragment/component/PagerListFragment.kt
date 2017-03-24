package com.github.chuross.qiiip.ui.view.fragment.component

import android.content.Context
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

abstract class PagerListFragment<VM: PagerListFragmentViewModel<ITEM>, ITEM> : BaseFragment<FragmentListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_list
    lateinit var viewModel: VM
    lateinit var itemAdapter: BaseItemAdapter<ITEM, *>

    abstract fun onCreateItemAdapter(): BaseItemAdapter<ITEM, *>

    abstract fun onCreateViewModel(context: Context): VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = onCreateViewModel(context)
        bindViewModel(viewModel)
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

        binding?.viewModel = viewModel
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

        viewModel.error
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe { it.second?.let { binding?.status?.showErrorView(it) } }
                .apply { viewModel.disposables.add(this) }

        if (viewModel.fetchedResult.get()?.isEmpty() ?: true) {
            viewModel.fetch()
        }
    }
}