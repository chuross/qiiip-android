package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.ui.activity.presenter.ScreenActivityPresenter
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate

class ScreenActivity : PresentationActivity<ScreenActivityPresenter>() {

    override fun createPresenter(): ScreenActivityPresenter = ScreenActivityPresenter(this, ScreenActivityTemplate(this))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}
