package com.github.chuross.qiiip.ui.activity.presenter

import android.content.Intent
import android.view.Gravity
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import com.github.chuross.qiiip.ui.activity.ScreenActivity
import com.github.chuross.qiiip.ui.activity.template.ScreenActivityTemplate
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragment
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragmentTransitionHelper
import com.github.chuross.qiiip.ui.widget.template.NavigationHeaderTemplate

class ScreenActivityPresenter(activity: ScreenActivity) : ActivityPresenter<ScreenActivity, ScreenActivityTemplate>(activity) {

    val headerTemplate by lazy { NavigationHeaderTemplate(template.navigation.getHeaderView(0)) }
    private val transitionHelper by lazy { ScreenFragmentTransitionHelper(R.id.container_main, view.supportFragmentManager) }

    override fun createTemplate(activity: ScreenActivity): ScreenActivityTemplate = ScreenActivityTemplate(activity)

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

    fun onLoginButtonClicked() {
        //TODO add state
        view.startActivity(Intent(Intent.ACTION_VIEW, QiitaV2Api.getAuthorizationUri(Application.from(view).requestContext, "")))
    }
}