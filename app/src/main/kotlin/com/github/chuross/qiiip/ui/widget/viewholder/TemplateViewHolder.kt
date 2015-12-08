package com.github.chuross.qiiip.ui.widget.viewholder

import android.support.v7.widget.RecyclerView
import com.github.chuross.library.mvp.view.template.SourceTemplate

class TemplateViewHolder<SOURCE> : RecyclerView.ViewHolder {

    val template: SourceTemplate<SOURCE>

    constructor(template: SourceTemplate<SOURCE>) : super(template.view) {
        this.template = template
    }
}