package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate
import kotlinx.android.synthetic.template_fragment_home_screen.view.layout_tab
import kotlinx.android.synthetic.template_fragment_home_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_home_screen.view.viewpager

class HomeScreenFragmentTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_fragment_home_screen) {

    val toolbar = view.toolbar

    val tabLayout = view.layout_tab

    val viewPager = view.viewpager
}