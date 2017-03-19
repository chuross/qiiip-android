package com.github.chuross.qiiip.ui.viewmodel.activity

import android.content.Context
import com.github.chuross.qiiip.application.screen.Screen
import jp.keita.kagurazaka.rxproperty.RxProperty

class ScreenActivityViewModel(override val context: Context) : ActivityViewModel() {

    var currentScreen: RxProperty<Screen> = RxProperty()

}