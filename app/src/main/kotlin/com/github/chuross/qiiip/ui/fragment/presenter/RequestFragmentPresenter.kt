package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.template.Template
import rx.Observable

abstract class RequestFragmentPresenter<F : Fragment, T : Template, R>(fragment: F) : SupportFragmentPresenter<F, T>(fragment) {

    abstract fun request(initialize: Boolean): Observable<R>
}