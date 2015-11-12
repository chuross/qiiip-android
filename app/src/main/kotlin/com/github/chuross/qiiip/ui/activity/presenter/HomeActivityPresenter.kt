package com.github.chuross.qiiip.ui.activity.presenter

import android.os.Bundle
import android.view.Gravity
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.qiiip.ui.activity.HomeActivity
import com.github.chuross.qiiip.ui.activity.template.HomeActivityTemplate

class HomeActivityPresenter(activity: HomeActivity, template: HomeActivityTemplate) : ActivityPresenter<HomeActivity, HomeActivityTemplate>(activity, template) {

    override fun create(savedInstanceState: Bundle?) {
        super.create(savedInstanceState)
    }

    override fun backPress(): Boolean {
        if (template.getDrawerLayout().isDrawerOpen(Gravity.START)) {
            template.getDrawerLayout().closeDrawer(Gravity.START)
            return true
        }
        return super.backPress()
    }
}