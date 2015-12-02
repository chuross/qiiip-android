package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.ui.fragment.screen.Screen

enum class Screen : com.github.chuross.qiiip.ui.fragment.screen.Screen {

    HOME {
        override val parent: Screen? = null
    },
    ENTRY_DETAIL {
        override val parent: Screen? = HOME

    }
}