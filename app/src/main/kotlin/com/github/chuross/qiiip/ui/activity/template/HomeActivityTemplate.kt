package com.github.chuross.qiiip.ui.activity.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate

import kotlinx.android.synthetic.template_activity_home.view.layout_drawer
import kotlinx.android.synthetic.template_activity_home.view.navigation

class HomeActivityTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_activity_home) {

    fun getDrawerLayout() = view.layout_drawer

    fun getNavigationView() = view.navigation

}