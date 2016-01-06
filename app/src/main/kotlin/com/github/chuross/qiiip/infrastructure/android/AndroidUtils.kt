package com.github.chuross.qiiip.infrastructure.android

import android.app.Activity
import android.view.WindowManager

class AndroidUtils {


    companion object {

        fun setStatusBarColor(activity: Activity, color: Int) {
            activity.window.apply {
                addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
                statusBarColor = color
            }
        }
    }

    private constructor()

}