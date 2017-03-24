package com.github.chuross.qiiip.application

import android.content.Context
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository

class Repositories(val context: Context) {

    val itemRepository: ItemRepository by lazy { ItemRepository().apply { application.component.inject(this) } }
    val tagRepository: TagRepository by lazy { TagRepository().apply { application.component.inject(this) } }
    private val application: Application get() = Application.from(context)
}