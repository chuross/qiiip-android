package com.github.chuross.qiiip.ui.viewmodel.fragment

import android.content.Context
import com.github.chuross.qiiip.domain.item.Item
import org.apache.commons.lang3.time.DateFormatUtils

class ItemDetailScreenFragmentViewModel(context: Context, val item: Item) : FragmentViewModel(context) {

    val createdAtText by lazy { DateFormatUtils.format(item.createdAt, "yyyy/MM/dd HH:mmに投稿") }
}