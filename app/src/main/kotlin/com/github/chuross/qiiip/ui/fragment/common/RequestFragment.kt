package com.github.chuross.qiiip.ui.fragment.common

import android.os.Bundle

abstract class RequestFragment<PRESENTER : RequestFragmentPresenter<*, TEMPLATE, R>, TEMPLATE : RequestTemplate, R> : BaseFragment<PRESENTER, TEMPLATE>() {

    override fun onViewCreated(template: TEMPLATE, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        presenter.request()
    }
}