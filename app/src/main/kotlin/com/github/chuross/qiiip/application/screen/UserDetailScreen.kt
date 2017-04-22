package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.view.fragment.screen.UserDetailScreenFragmentBuilder

class UserDetailScreen(val user: User) : Screen {

    override val identity: String = UserDetailScreen::class.java.name

    override fun createFragment(): Fragment = UserDetailScreenFragmentBuilder(user).build()
}