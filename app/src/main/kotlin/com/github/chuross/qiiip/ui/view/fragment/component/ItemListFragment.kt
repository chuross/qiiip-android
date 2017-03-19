package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentItemListBinding
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.ItemListFragmentViewModel
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.BackpressureStrategy
import timber.log.Timber

class ItemListFragment : BaseFragment<FragmentItemListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_item_list
    private lateinit var viewModel: ItemListFragmentViewModel

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ItemListFragmentViewModel(context)

        val adapter = ItemAdapter(context, viewModel.items.toFlowable(BackpressureStrategy.LATEST))
        adapter.setOnItemClickListener { viewHolder, index, item ->
            Toast.makeText(context, "item:$index", Toast.LENGTH_SHORT).show()
        }

        binding?.swipeRefreshLayout?.apply {
            setColorSchemeResources(R.color.colorPrimary)
            setOnRefreshListener { viewModel.fetchItems() }
        }
        binding?.list?.apply {
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }

        viewModel.isLoading
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe({
                    Timber.d("isLoading:$it")
                    binding?.swipeRefreshLayout?.isRefreshing = it
                })
                .apply { viewModel.disposables.add(this) }
        viewModel.fetchItems()
    }
}