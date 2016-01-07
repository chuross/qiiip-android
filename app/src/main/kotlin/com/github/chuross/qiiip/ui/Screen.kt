package com.github.chuross.qiiip.ui

import com.github.chuross.qiiip.ui.fragment.screen.Screen

enum class Screen : Screen {

    HOME {
        override val isRoot: Boolean = true
        override val parent: Screen? = null
    },
    ITEM_DETAIL {
        override val isRoot: Boolean = false
        override val parent: Screen? = HOME
    },
    USER {
        override val isRoot: Boolean = false
        override val parent: Screen? = null
    },
    TAG_ITEM_LIST {
        override val isRoot: Boolean = false
        override val parent: Screen? = null

    }
}