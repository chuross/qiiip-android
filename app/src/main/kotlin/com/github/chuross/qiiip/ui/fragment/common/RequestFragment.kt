package com.github.chuross.qiiip.ui.fragment.common

import android.os.AsyncTask
import android.os.Bundle
import com.github.chuross.qiiip.ui.fragment.presenter.RequestFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.RequestTemplate
import rx.schedulers.Schedulers

abstract class RequestFragment<PRESENTER : RequestFragmentPresenter<*, TEMPLATE, R>, TEMPLATE : RequestTemplate, R> : BaseFragment<PRESENTER, TEMPLATE>() {

    abstract fun onRequestSuccess(result: R)

    open fun onRequestFailed(error: Throwable) {
    }

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        template.messageView.retryCallback = {
            request()
        }

        request()
    }

    protected fun request() {
        presenter.request().compose(complement<R>(Schedulers.from(AsyncTask.THREAD_POOL_EXECUTOR))).subscribe({ result ->
            onRequestSuccess(result)
        }, { error ->
            onRequestFailed(error)
            presenter.template.messageView.showErrorMessage(error)
        })
    }
}