package com.github.chuross.qiiip.ui.widget.adapter

import android.content.Context
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.widget.template.ItemRowTemplate
import com.github.chuross.qiiip.ui.widget.TemplateRecyclerViewHolder

class ItemArrayAdapter : TemplateArrayAdapter<Item> {

    constructor(context: Context) : super(context)

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): TemplateRecyclerViewHolder<Item> = TemplateRecyclerViewHolder(ItemRowTemplate(context))

    override fun onBindViewHolder(holder: TemplateRecyclerViewHolder<Item>, position: Int) = holder.template.apply(get(position))

}