package com.github.chuross.qiiip.ui.fragment.template

import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.ui.widget.MultiStateView

interface RequestTemplate : Template {

    val stateView: MultiStateView
}