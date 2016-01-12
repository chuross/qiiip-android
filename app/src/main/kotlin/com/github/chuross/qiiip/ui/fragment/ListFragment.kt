package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

abstract class ListFragment<P : RequestFragmentPresenter<*, List<R>>, T : ListTemplate, R> : RequestFragment<P, T, List<R>>() {

    abstract val adapter: RecyclerViewCollectionAdapter<*, *>

    abstract val layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        template.list.adapter = adapter
        template.list.layoutManager = layoutManager
        template.list.setHasFixedSize(true)
    }

    override fun onRequestSuccess(result: List<R>, initialize: Boolean) {
        super.onRequestSuccess(result, initialize)

        if (initialize) {
            adapter.clear()
        }
        adapter.addAll(result)
    }
}