package com.github.chuross.qiiip.ui.widget

import android.support.v7.widget.RecyclerView
import com.github.chuross.library.mvp.view.template.SourceTemplate

class TemplateRecyclerViewHolder<SOURCE> : RecyclerView.ViewHolder {

    val template: SourceTemplate<SOURCE>

    constructor(template: SourceTemplate<SOURCE>) : super(template.view) {
        this.template = template
    }
}