package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate

abstract class ListFragment<PRESENTER : RequestFragmentPresenter<*, TEMPLATE, R>, TEMPLATE : ListTemplate, R> : RequestFragment<PRESENTER, TEMPLATE, R>() {

    abstract val adapter: RecyclerView.Adapter<*>

    abstract val layoutManager: RecyclerView.LayoutManager

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        template.list.adapter = adapter
        template.list.layoutManager = layoutManager
        template.list.setHasFixedSize(true)
    }

}