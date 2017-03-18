package com.github.chuross.qiiip.ui.view.fragment.component

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentItemListBinding
import com.github.chuross.qiiip.ui.adapter.ItemAdapter
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.ItemListFragmentViewModel
import io.reactivex.BackpressureStrategy

class ItemListFragment : BaseFragment<FragmentItemListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_item_list
    private lateinit var viewModel: ItemListFragmentViewModel

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ItemListFragmentViewModel(context)

        val adapter = ItemAdapter(context, viewModel.items.toFlowable(BackpressureStrategy.LATEST))
        adapter.setOnItemClickListener { viewHolder, index, item ->
        }

        binding?.list?.apply {
            layoutManager = LinearLayoutManager(context)
            setAdapter(adapter)
        }

        viewModel.fetchItems()
    }
}