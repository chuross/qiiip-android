package com.github.chuross.qiiip.ui.fragment

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.RequestTemplate
import rx.schedulers.Schedulers

abstract class RequestFragment<P : RequestFragmentPresenter<*, out RequestTemplate, R>, R> : BaseFragment<P>() {

    open fun onRequestSuccess(result: R, initialize: Boolean) {
    }

    open fun onRequestFailed(error: Throwable, initialize: Boolean) {
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        presenter.template.messageView.retryCallback = {
            request(false)
        }

        request(true)
    }

    protected fun request(initialize: Boolean) {
        subscriptions.add(presenter.request(initialize).compose(complement<R>(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))).subscribe({ result ->
            onRequestSuccess(result, initialize)
        }, { error ->
            onRequestFailed(error, initialize)
            presenter.template.messageView.showErrorMessage(error)
        }))
    }
}