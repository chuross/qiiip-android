package com.github.chuross.qiiip.ui.activity.template

import android.content.Context
import android.support.v4.widget.DrawerLayout
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate

class HomeActivityTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_activity_home) {

    val drawerLayout: DrawerLayout by lazy { view.findViewById(R.id.layout_drawer) as DrawerLayout }
}