package com.github.chuross.qiiip.ui.fragment.screen

interface ScreenFragment {

    val screen: Screen

    val screenExitAction: ScreenExitAction

    val screenIdentity: String
}