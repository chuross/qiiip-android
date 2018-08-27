package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentListBinding
import com.github.chuross.qiiip.ui.adapter.LoadingMoreViewItem
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import com.github.chuross.recyclerviewadapters.BaseItemAdapter
import com.github.chuross.recyclerviewadapters.CompositeRecyclerAdapter
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

abstract class PagerListFragment<VM : PagerListFragmentViewModel<ITEM>, ITEM> : BaseFragment<FragmentListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_list
    lateinit var itemAdapter: BaseItemAdapter<ITEM, *>
    lateinit var viewModel: VM

    abstract fun onCreateViewModel(): VM

    abstract fun onCreateItemAdapter(): BaseItemAdapter<ITEM, *>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = onCreateViewModel()
        viewModel.fetch()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = CompositeRecyclerAdapter()

        itemAdapter = onCreateItemAdapter()

        val loadingAdapter = LoadingMoreViewItem(requireContext(), View.OnClickListener {
            viewModel.fetchNext()
        })
        loadingAdapter.isVisible = false

        adapter.add(itemAdapter)
        adapter.add(loadingAdapter)

        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.swipeRefreshLayout.also {
            it.setColorSchemeResources(R.color.colorPrimary)
            it.setOnRefreshListener { viewModel.fetch() }
        }
        binding.list.also {
            it.setHasFixedSize(true)
            it.layoutManager = LinearLayoutManager(context)
            it.adapter = adapter
        }
        binding.status.retryListener = { viewModel.fetch() }

        viewModel.isLoading
                .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .filter { it }
                .subscribe {
                    binding.status.showLoadingView()
                }.also { viewModel.disposables.add(it) }

        viewModel.list
                .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribe {
                    binding.status.hideAll()
                    loadingAdapter.isVisible = !it.isEmpty()
                }.also { viewModel.disposables.add(it) }

        viewModel.fail
                .filter { viewModel.currentPageValue <= viewModel.defaultPage }
                .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribe { binding.status.showErrorView(it) }
                .also { viewModel.disposables.add(it) }
    }
}