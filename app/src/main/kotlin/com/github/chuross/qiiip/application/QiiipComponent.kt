package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.usecase.AuthorizeAccount
import com.github.chuross.qiiip.usecase.item.GetFeedItems
import com.github.chuross.qiiip.usecase.item.GetItems
import com.github.chuross.qiiip.usecase.item.GetItemsByTagId
import com.github.chuross.qiiip.usecase.item.GetStokeItems
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(QiiipModule::class, RepositoryModule::class))
interface QiiipComponent {

    fun inject(repository: ItemRepository)

    fun inject(repository: TagRepository)

    fun inject(useCase: AuthorizeAccount)

    fun inject(useCase: GetItems)

    fun inject(useCase: GetItemsByTagId)

    fun inject(useCase: GetStokeItems)

    fun inject(useCase: GetFeedItems)
}