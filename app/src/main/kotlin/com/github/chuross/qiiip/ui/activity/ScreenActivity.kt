package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.ui.activity.presenter.ScreenActivityPresenter
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate
import com.github.chuross.qiiip.ui.fragment.screen.HomeScreenFragment
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragment

class ScreenActivity : PresentationActivity<ScreenActivityPresenter>() {

    override fun createPresenter(): ScreenActivityPresenter = ScreenActivityPresenter(this, ScreenActivityTemplate(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchScreen(HomeScreenFragment())
    }

    fun setUpToolbar(toolbar: Toolbar) {
        toolbar.setNavigationIcon(R.drawable.abc_ic_ab_back_mtrl_am_alpha)
        toolbar.setNavigationOnClickListener {
            presenter.homeAsUp()
        }
    }

    fun launchScreen(screenFragment: ScreenFragment) {
        presenter.launchScreen(screenFragment)
    }
}
