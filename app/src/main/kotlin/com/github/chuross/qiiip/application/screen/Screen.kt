package com.github.chuross.qiiip.application.screen

import android.support.v4.app.Fragment
import java.io.Serializable

interface Screen: Serializable {

    val identity: String
    val fragment: Fragment get() = createFragment()

    fun createFragment(): Fragment
}