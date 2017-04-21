package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.ui.view.fragment.screen.MyItemListScreenFragment

class MyItemListScreen : Screen {

    override val identity: String = MyItemListScreen::class.java.name

    override fun createFragment(): Fragment = MyItemListScreenFragment()
}