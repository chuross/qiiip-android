package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import rx.Observable

abstract class RequestFragmentPresenter<F : Fragment, R>(fragment: F) : SupportFragmentPresenter<F>(fragment) {

    abstract fun request(initialize: Boolean): Observable<R>
}