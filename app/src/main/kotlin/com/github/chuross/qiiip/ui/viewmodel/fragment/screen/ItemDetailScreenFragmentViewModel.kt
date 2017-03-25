package com.github.chuross.qiiip.ui.viewmodel.fragment.screen

import android.content.Context
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.FragmentViewModel
import org.apache.commons.lang3.time.DateFormatUtils

class ItemDetailScreenFragmentViewModel(context: Context, val item: Item) : FragmentViewModel(context) {

    val createdAtText: String by lazy { DateFormatUtils.format(item.createdAt, "yyyy/MM/dd HH:mmに投稿") }
}