package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.qiiip.ui.fragment.presenter.EmptyFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ErrorMessageTemplate

class ErrorMessageFragment : BaseFragment<EmptyFragmentPresenter<ErrorMessageFragment, ErrorMessageTemplate>, ErrorMessageTemplate>() {

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ErrorMessageTemplate = ErrorMessageTemplate(activity)

    override fun createPresenter(): EmptyFragmentPresenter<ErrorMessageFragment, ErrorMessageTemplate> = EmptyFragmentPresenter(this)

}