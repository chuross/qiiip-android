package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.PagerListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ListTemplate
import com.jakewharton.rxbinding.support.v7.widget.RxRecyclerView

abstract class PagerListFragment<P : PagerListFragmentPresenter<*, T, R>, T : ListTemplate, R> : ListFragment<P, T, R>() {

    override fun onViewCreated(template: T, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        RxRecyclerView.scrollEvents(template.list)
                .filter { event -> adapter.itemCount > 0 && !event.view().canScrollVertically(1) }
                .subscribe({
                    request(false)
                })
    }
}