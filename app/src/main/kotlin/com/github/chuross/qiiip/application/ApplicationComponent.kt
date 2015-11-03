package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(QiipModule::class))
interface ApplicationComponent {

    fun inject(repository: ItemRepository)

    fun inject(repository: TagRepository)
}