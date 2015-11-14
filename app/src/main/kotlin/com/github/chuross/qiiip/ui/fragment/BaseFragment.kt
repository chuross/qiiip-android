package com.github.chuross.qiiip.ui.fragment

import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.fragment.SupportPresentationFragment
import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.ui.activity.ScreenActivity

abstract class BaseFragment<PRESENTER : SupportFragmentPresenter<*, TEMPLATE>, TEMPLATE : Template> : SupportPresentationFragment<PRESENTER, TEMPLATE>() {

    val screenActivity by lazy { activity as ScreenActivity }

}