package com.github.chuross.qiiip.ui.fragment

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.RequestTemplate
import rx.schedulers.Schedulers

abstract class RequestFragment<P : RequestFragmentPresenter<*, R>, T : RequestTemplate, R> : BaseFragment<P, T>() {

    open fun onRequestSuccess(result: R, initialize: Boolean) {
    }

    open fun onRequestFailed(error: Throwable, initialize: Boolean) {
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        template.stateView.retryCallback = {
            request(false)
        }

        request(true)
    }

    protected fun request(initialize: Boolean) {
        template.stateView.showLoadingView()

        subscriptions.add(presenter.request(initialize).compose(complement<R>(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))).subscribe({ result ->
            template.stateView.hideAll()
            onRequestSuccess(result, initialize)
        }, { error ->
            template.stateView.showErrorView(error)
            onRequestFailed(error, initialize)
        }))
    }
}