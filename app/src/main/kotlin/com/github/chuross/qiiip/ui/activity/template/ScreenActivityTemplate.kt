package com.github.chuross.qiiip.ui.activity.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate

import kotlinx.android.synthetic.template_activity_screen.view.layout_drawer
import kotlinx.android.synthetic.template_activity_screen.view.navigation

class ScreenActivityTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_activity_screen) {

    val drawerLayout = view.layout_drawer

    val navigation = view.navigation
}