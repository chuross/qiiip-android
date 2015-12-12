package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.PagerListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView

abstract class PagerListFragment<PRESENTER : PagerListFragmentPresenter<*, TEMPLATE, R>, TEMPLATE : ListTemplate, R> : ListFragment<PRESENTER, TEMPLATE, R>() {

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        RxRecyclerView.scrollEvents(template.list)
                .filter { event -> adapter.itemCount > 0 && !event.view().canScrollVertically(1) }
                .subscribe({
                    request(false)
                })
    }
}