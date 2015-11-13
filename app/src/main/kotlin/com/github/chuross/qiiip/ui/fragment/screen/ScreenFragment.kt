package com.github.chuross.qiiip.ui.fragment.screen

interface ScreenFragment {

    fun getScreen(): Screen

    fun getScreenExitAction(): ScreenExitAction

    fun getScreenIdentity(): String
}