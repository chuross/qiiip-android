package com.github.chuross.qiiip.ui.activity.presenter

import android.view.Gravity
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.qiiip.ui.activity.ScreenActivity
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate

class ScreenActivityPresenter(activity: ScreenActivity, template: ScreenActivityTemplate) : ActivityPresenter<ScreenActivity, ScreenActivityTemplate>(activity, template) {

    override fun backPress(): Boolean {
        if (template.drawerLayout.isDrawerOpen(Gravity.START)) {
            template.drawerLayout.closeDrawer(Gravity.START)
            return true
        }
        return super.backPress()
    }
}