package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.chuross.qiiip.R
import rx.Observable

abstract class PagerListFragmentPresenter<F : Fragment, RESULT>(fragment: F) : RequestFragmentPresenter<F, List<RESULT>>(fragment) {

    companion object {
        private val DEFAULT_PAGE = 1
    }

    var currentPage = DEFAULT_PAGE
    val perPage by lazy { view.resources.getInteger(R.integer.per_page) }

    abstract fun request(page: Int, initialize: Boolean): Observable<List<RESULT>>

    override fun request(initialize: Boolean): Observable<List<RESULT>> {
        return request(if (!initialize) currentPage else DEFAULT_PAGE, initialize).doOnNext {
            currentPage = currentPage.inc()
        }
    }

}