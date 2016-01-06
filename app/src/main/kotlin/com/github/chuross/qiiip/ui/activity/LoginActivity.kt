package com.github.chuross.qiiip.ui.activity

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.activity.presenter.LoginActivityPresenter
import rx.schedulers.Schedulers

class LoginActivity : BaseActivity<LoginActivityPresenter>() {

    override fun createPresenter(): LoginActivityPresenter = LoginActivityPresenter(this).apply {
        Application.from(this@LoginActivity).component.inject(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        subscriptions.add(presenter.request()
                .compose(complement<User>(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR)))
                .subscribe({
                    presenter.onRequestSuccess()
                }, { tr ->
                    presenter.onRequestFailed(tr)
                }))
    }
}