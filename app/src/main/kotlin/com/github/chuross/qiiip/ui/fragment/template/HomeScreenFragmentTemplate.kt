package com.github.chuross.qiiip.ui.fragment.template

import android.content.Context
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.library.mvp.view.template.NonApplyTemplate
import kotlinx.android.synthetic.template_fragment_home_screen.view.toolbar
import kotlinx.android.synthetic.template_fragment_home_screen.view.viewpager

class HomeScreenFragmentTemplate(context: Context) : NonApplyTemplate(context, R.layout.template_fragment_home_screen) {

    val toolbar by lazy { view.toolbar }

    val viewPager by lazy { view.viewpager }
}