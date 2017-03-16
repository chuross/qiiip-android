package com.github.chuross.qiiip.ui.viewmodel

import android.content.Context
import com.github.chuross.qiiip.application.screen.Screen
import com.github.chuross.qiiip.ui.view.activity.ScreenActivity
import com.github.chuross.qiiip.ui.view.fragment.ScreenFragment

class ScreenActivityViewModel(override val context: Context) : ActivityViewModel() {

    fun isDifferentScreen(activity: ScreenActivity, screen: Screen): Boolean {
        return activity.binding?.screenContainer?.let {
            val currentFragment = activity.supportFragmentManager.findFragmentById(it.id)
            if (currentFragment !is ScreenFragment) return@let true
            
            currentFragment.screen.identity != screen.identity
        } ?: false
    }
}