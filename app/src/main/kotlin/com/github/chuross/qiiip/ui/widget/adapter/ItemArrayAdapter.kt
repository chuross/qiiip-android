package com.github.chuross.qiiip.ui.widget.adapter

import android.content.Context
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.template.ItemRowTemplate
import com.github.chuross.qiiip.ui.widget.viewholder.TemplateViewHolder

class ItemArrayAdapter : TemplateArrayAdapter<Item> {

    constructor(context: Context) : super(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TemplateViewHolder<Item> = TemplateViewHolder(ItemRowTemplate(context))

    override fun onBindViewHolder(holder: TemplateViewHolder<Item>, position: Int) = holder.template.apply(get(position))

}