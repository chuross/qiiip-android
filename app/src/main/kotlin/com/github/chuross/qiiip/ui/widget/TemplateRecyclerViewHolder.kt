package com.github.chuross.qiiip.ui.widget

import android.support.v7.widget.RecyclerView
import com.github.chuross.library.mvp.view.template.ApplicableTemplate

class TemplateRecyclerViewHolder<SOURCE> : RecyclerView.ViewHolder {

    val template: ApplicableTemplate<SOURCE>

    constructor(template: ApplicableTemplate<SOURCE>) : super(template.view) {
        this.template = template
    }
}