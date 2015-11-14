package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.ui.fragment.screen.Screen

enum class Screen : com.github.chuross.qiiip.ui.fragment.screen.Screen {

    HOME {
        override fun getParent(): Screen? = null
    },
    ENTRY_DETAIL {
        override fun getParent(): Screen? = HOME
    }
}