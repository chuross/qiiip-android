package com.github.chuross.qiiip.ui.adapter

import android.databinding.ViewDataBinding
import android.support.v7.widget.RecyclerView

class BindingViewHolder<out B : ViewDataBinding>(val binding: B) : RecyclerView.ViewHolder(binding.root)