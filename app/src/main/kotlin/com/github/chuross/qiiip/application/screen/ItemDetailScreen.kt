package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.view.fragment.screen.ItemDetailScreenFragmentBuilder

class ItemDetailScreen(val item: Item) : Screen {

    override val identity: String get() = "${ItemDetailScreen::class.java.name}/${item.identity.value}"

    override fun createFragment(): Fragment = ItemDetailScreenFragmentBuilder(item).build()
}