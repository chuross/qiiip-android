package com.github.chuross.qiiip.ui.widget.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.ui.widget.TemplateRecyclerViewHolder
import java.util.*

abstract class TemplateArrayAdapter<T> : RecyclerView.Adapter<TemplateRecyclerViewHolder<T>> {

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

    fun add(item: T) {
        synchronized(lock, {
            items.add(item)
        })
        notifyDataSetChanged()
    }

    fun add(position: Int, item: T) {
        synchronized(lock, {
            items.add(position, item)
        })
        notifyDataSetChanged()
    }

    fun addAll(items: List<T>) {
        synchronized(lock, {
            this.items.addAll(items)
        })
        notifyDataSetChanged()
    }

    fun remove(position: Int) {
        synchronized(lock, {
            items.removeAt(position)
        })
        notifyDataSetChanged()
    }

    fun clear() {
        synchronized(lock, {
            items.clear()
        })
        notifyDataSetChanged()
    }

}