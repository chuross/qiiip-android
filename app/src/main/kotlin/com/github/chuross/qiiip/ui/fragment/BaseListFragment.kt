package com.github.chuross.qiiip.ui.fragment

import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.template.Template

abstract class BaseListFragment<PRESENTER : SupportFragmentPresenter<*, TEMPLATE>, TEMPLATE : Template> : BaseFragment<PRESENTER, TEMPLATE>() {
    

}