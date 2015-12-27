package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import android.support.v7.widget.Toolbar
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.ui.activity.presenter.ScreenActivityPresenter
import com.github.chuross.qiiip.ui.fragment.screen.HomeScreenFragment
import com.github.chuross.qiiip.ui.fragment.screen.ScreenFragment
import rx.android.schedulers.AndroidSchedulers

class ScreenActivity : PresentationActivity<ScreenActivityPresenter>() {


    override fun createPresenter(): ScreenActivityPresenter = ScreenActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        launchScreen(HomeScreenFragment())

        Application.from(this).preferences.authenticationChangeEvent
                .subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ user ->
                    presenter.headerTemplate.apply(user)
                }, {})

        presenter.headerTemplate.apply(Application.from(this).preferences.getAuthenticatedUser())
        presenter.headerTemplate.loginButton.setOnClickListener {
            presenter.onLoginButtonClicked()
        }
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
