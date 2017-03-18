package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.ui.view.fragment.screen.HomeScreenFragment

class HomeScreen : Screen {

    override val identity: String = HomeScreen::class.java.name

    override fun createFragment(): Fragment = HomeScreenFragment()
}