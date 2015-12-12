package com.github.chuross.qiiip.ui.fragment

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.RequestTemplate
import rx.schedulers.Schedulers

abstract class RequestFragment<PRESENTER : RequestFragmentPresenter<*, TEMPLATE, R>, TEMPLATE : RequestTemplate, R> : BaseFragment<PRESENTER, TEMPLATE>() {

    abstract fun onRequestSuccess(result: R, initialize: Boolean)

    open fun onRequestFailed(error: Throwable, initialize: Boolean) {
    }

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        template.messageView.retryCallback = {
            request(false)
        }

        request(true)
    }

    protected fun request(initialize: Boolean) {
        presenter.request(initialize).compose(complement<R>(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))).subscribe({ result ->
            onRequestSuccess(result, initialize)
        }, { error ->
            onRequestFailed(error, initialize)
            presenter.template.messageView.showErrorMessage(error)
        })
    }
}