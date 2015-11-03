package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository

class Application : android.app.Application() {

    val component = DaggerApplicationComponent.builder().qiipModule(QiipModule()).build()

    fun getItemRepository(): ItemRepository {
        val repository = ItemRepository()
        component.inject(repository)
        return repository
    }

    fun getTagRepository(): TagRepository {
        val repository = TagRepository()
        component.inject(repository)
        return repository
    }
}