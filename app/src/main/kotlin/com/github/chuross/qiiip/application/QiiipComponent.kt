package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.usecase.AuthorizeAccount
import com.github.chuross.qiiip.usecase.GetFeeds
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(QiiipModule::class))
interface QiiipComponent {

    fun inject(repository: ItemRepository)

    fun inject(repository: TagRepository)

    fun inject(useCase: AuthorizeAccount)

    fun inject(useCase: GetFeeds)
}