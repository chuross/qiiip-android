package com.github.chuross.qiiip.ui.widget.adapter

import android.support.v7.widget.RecyclerView

abstract class RecyclerViewCollectionAdapter<E, VH : RecyclerView.ViewHolder> : RecyclerView.Adapter<VH>() {

    abstract fun add(item: E)

    abstract fun add(position: Int, item: E)

    abstract fun addAll(items: List<E>)

    abstract fun remove(item: E)

    abstract fun removeAt(position: Int)

    abstract fun clear()

    abstract fun isEmpty(): Boolean
}