package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

abstract class ListFragment<P : RequestFragmentPresenter<*, out ListTemplate, List<R>>, R> : RequestFragment<P, List<R>>() {

    abstract val adapter: RecyclerViewCollectionAdapter<*, *>

    abstract val layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        presenter.template.list.adapter = adapter
        presenter.template.list.layoutManager = layoutManager
        presenter.template.list.setHasFixedSize(true)
    }

    override fun onRequestSuccess(result: List<R>, initialize: Boolean) {
        super.onRequestSuccess(result, initialize)

        if (initialize) {
            adapter.clear()
        }
        adapter.addAll(result)
    }
}