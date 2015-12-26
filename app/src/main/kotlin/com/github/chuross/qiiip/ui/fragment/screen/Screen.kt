package com.github.chuross.qiiip.ui.fragment.screen

interface Screen {

    val isRoot: Boolean

    val parent: Screen?
}