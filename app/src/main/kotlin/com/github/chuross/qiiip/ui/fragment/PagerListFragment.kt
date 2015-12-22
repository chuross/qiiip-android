package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.PagerListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView

abstract class PagerListFragment<P : PagerListFragmentPresenter<*, out ListTemplate, List<R>>, R> : ListFragment<P, R>() {

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        RxRecyclerView.scrollEvents(presenter.template.list)
                .filter { event -> !adapter.isEmpty() && !event.view().canScrollVertically(1) }
                .subscribe({
                    request(false)
                })
    }

}