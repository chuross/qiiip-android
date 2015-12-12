package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

abstract class ListFragment<P : RequestFragmentPresenter<*, T, R>, T : ListTemplate, R> : RequestFragment<P, T, R>() {

    abstract val adapter: RecyclerViewCollectionAdapter<*, *>

    abstract val layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(template: T, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        template.list.adapter = adapter
        template.list.layoutManager = layoutManager
        template.list.setHasFixedSize(true)
    }
}