package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.ui.activity.presenter.HomeActivityPresenter
import com.github.chuross.qiiip.ui.activity.template.HomeActivityTemplate

class HomeActivity : PresentationActivity<HomeActivityPresenter>() {

    override fun createPresenter(): HomeActivityPresenter = HomeActivityPresenter(this, HomeActivityTemplate(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
