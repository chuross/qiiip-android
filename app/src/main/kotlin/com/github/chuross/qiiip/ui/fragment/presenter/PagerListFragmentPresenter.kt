package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.view.template.Template
import rx.Observable

abstract class PagerListFragmentPresenter<F : Fragment, T : Template, R>(fragment: F) : RequestFragmentPresenter<F, T, R>(fragment) {

    var currentPage = DEFAULT_PAGE

    companion object {
        private val DEFAULT_PAGE = 1
    }

    abstract fun request(page: Int, initialize: Boolean): Observable<R>

    override fun request(initialize: Boolean): Observable<R> {
        return request(if (!initialize) currentPage else DEFAULT_PAGE, initialize).doOnNext {
            currentPage = currentPage.inc()
        }
    }

}