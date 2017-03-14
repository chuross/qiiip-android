package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment

interface Screen {

    val fragment: Fragment get() = createFragment()

    fun createFragment(): Fragment
}