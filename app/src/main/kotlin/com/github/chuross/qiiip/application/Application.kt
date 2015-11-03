package com.github.chuross.qiiip.application

import android.app.Activity
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository

open class Application : android.app.Application() {

    val component: ApplicationComponent

    companion object {

        fun from(activity: Activity): Application = activity.application as Application
    }

    init {
        component = DaggerApplicationComponent.builder().qiipModule(newQiipModule()).build()
    }

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

    open fun newQiipModule(): QiipModule = QiipModule()
}