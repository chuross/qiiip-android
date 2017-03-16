package com.github.chuross.qiiip.ui.view.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentItemListBinding

class ItemListFragment : BaseFragment<FragmentItemListBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_item_list

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.list?.apply {
            layoutManager = LinearLayoutManager(context)
        }
    }
}