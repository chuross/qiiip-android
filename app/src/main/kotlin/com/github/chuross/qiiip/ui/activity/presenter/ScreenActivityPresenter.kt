package com.github.chuross.qiiip.ui.activity.presenter

import android.view.Gravity
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.qiiip.ui.activity.ScreenActivity
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragment
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragmentTransitionHelper

class ScreenActivityPresenter(activity: ScreenActivity, template: ScreenActivityTemplate) : ActivityPresenter<ScreenActivity, ScreenActivityTemplate>(activity, template) {

    private val transitionHelper by lazy { ScreenFragmentTransitionHelper(R.id.container_main, view.supportFragmentManager) }

    override fun backPress(): Boolean {
        if (template.drawerLayout.isDrawerOpen(Gravity.START)) {
            template.drawerLayout.closeDrawer(Gravity.START)
            return true
        }
        return transitionHelper.popBackStack() || super.backPress()
    }

    fun homeAsUp() {
        transitionHelper.homeAsUp()
    }

    fun launchScreen(screenFragment: ScreenFragment) {
        transitionHelper.launchScreen(screenFragment)
    }
}