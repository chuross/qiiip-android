package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.ui.view.fragment.ItemListFragment

class ItemListScreen : Screen {

    override val identity: String = ItemListScreen::class.java.name

    override fun createFragment(): Fragment = ItemListFragment()
}