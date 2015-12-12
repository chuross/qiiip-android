package com.github.chuross.qiiip.ui.widget.adapter

import android.content.Context
import com.github.chuross.qiiip.ui.widget.TemplateRecyclerViewHolder
import java.util.*

abstract class TemplateArrayAdapter<T> : RecyclerViewCollectionAdapter<T, TemplateRecyclerViewHolder<T>> {

    val lock = Object()
    val items: ArrayList<T>
    val context: Context

    constructor(context: Context) : this(context, ArrayList<T>())

    constructor(context: Context, items: ArrayList<T>) {
        this.context = context
        this.items = items
    }

    override fun getItemCount(): Int = items.size

    fun get(position: Int) = items[position]

    override fun add(item: T) {
        synchronized(lock, {
            items.add(item)
        })
        notifyDataSetChanged()
    }

    override fun add(position: Int, item: T) {
        synchronized(lock, {
            items.add(position, item)
        })
        notifyDataSetChanged()
    }

    override fun addAll(items: List<T>) {
        synchronized(lock, {
            this.items.addAll(items)
        })
        notifyDataSetChanged()
    }

    override fun remove(item: T) {
        synchronized(lock, {
            this.items.remove(item)
        })
        notifyDataSetChanged()
    }

    override fun removeAt(position: Int) {
        synchronized(lock, {
            items.removeAt(position)
        })
        notifyDataSetChanged()
    }

    override fun clear() {
        synchronized(lock, {
            items.clear()
        })
        notifyDataSetChanged()
    }

}