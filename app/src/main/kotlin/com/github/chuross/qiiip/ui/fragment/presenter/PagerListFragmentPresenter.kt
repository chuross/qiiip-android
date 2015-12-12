package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.view.template.Template
import rx.Observable

abstract class PagerListFragmentPresenter<FRAGMENT : Fragment, TEMPLATE : Template, R>(fragment: FRAGMENT) : RequestFragmentPresenter<FRAGMENT, TEMPLATE, R>(fragment) {

    var currentPage = 0

    abstract fun request(page: Int, initialize: Boolean): Observable<R>

    override fun request(initialize: Boolean): Observable<R> {
        currentPage = if (!initialize) currentPage.inc() else 1
        return request(currentPage, initialize)
    }

}