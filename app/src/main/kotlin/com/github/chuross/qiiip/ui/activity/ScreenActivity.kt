package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.ui.activity.presenter.ScreenActivityPresenter
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragment

class ScreenActivity : PresentationActivity<ScreenActivityPresenter>() {

    override fun createPresenter(): ScreenActivityPresenter = ScreenActivityPresenter(this, ScreenActivityTemplate(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    fun launchScreen(screenFragment: ScreenFragment) {
        presenter.launchScreen(screenFragment)
    }
}
