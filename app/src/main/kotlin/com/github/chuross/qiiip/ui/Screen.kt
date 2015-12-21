package com.github.chuross.qiiip.ui

import com.github.chuross.qiiip.ui.fragment.screen.Screen

enum class Screen : Screen {

    HOME {
        override val parent: Screen? = null
    },
    ITEM_DETAIL {
        override val parent: Screen? = HOME
    }
}