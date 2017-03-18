package com.github.chuross.qiiip.ui.viewmodel.activity

import android.content.Context
import com.github.chuross.qiiip.application.screen.Screen
import jp.keita.kagurazaka.rxproperty.RxProperty

class ScreenActivityViewModel(override val context: Context) : ActivityViewModel() {

    var currentScreen: RxProperty<Screen> = RxProperty()

    fun isDifferentScreen(screen: Screen): Boolean {
        return currentScreen.get()?.let { it.identity != screen.identity } ?: true
    }
}