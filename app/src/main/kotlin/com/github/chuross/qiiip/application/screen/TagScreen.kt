package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.view.fragment.screen.TagScreenFragmentBuilder

class TagScreen(val tag: Tag) : Screen {

    override val identity: String get() = "${TagScreen::class.java.name}/${tag.identity.value}"

    override fun createFragment(): Fragment = TagScreenFragmentBuilder(tag).build()
}