package com.github.chuross.qiiip.ui.activity

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.ui.activity.presenter.LoginActivityPresenter
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class LoginActivity : PresentationActivity<LoginActivityPresenter>() {

    override fun createPresenter(): LoginActivityPresenter = LoginActivityPresenter(this).apply {
        Application.from(this@LoginActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter.request()
                .subscribeOn(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    presenter.onRequestSuccess()
                }, { tr ->
                    presenter.onRequestFailed(tr)
                })
    }
}