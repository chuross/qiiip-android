package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.domain.user.UserRepository
import com.github.chuross.qiiip.ui.activity.presenter.LoginActivityPresenter
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(QiipModule::class))
interface ApplicationComponent {

    fun inject(presenter: LoginActivityPresenter)

    fun inject(repository: UserRepository)

    fun inject(repository: ItemRepository)

    fun inject(repository: TagRepository)
}