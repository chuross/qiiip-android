package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideItemRepository(api: QiitaV2Api): ItemRepository {
        return ItemRepository().apply { this.api = api }
    }

    @Provides
    fun provideTagRepository(api: QiitaV2Api): TagRepository {
        return TagRepository().apply { this.api = api }
    }
}