package com.github.chuross.qiiip.application

import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.item.StockService
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import dagger.Module
import dagger.Provides

@Module
class DomainModule {

    @Provides
    fun provideItemRepository(api: QiitaV2Api): ItemRepository {
        return ItemRepository().also { it.api = api }
    }

    @Provides
    fun provideStockService(api: QiitaV2Api): StockService {
        return StockService().also { it.api = api }
    }

    @Provides
    fun provideTagRepository(api: QiitaV2Api): TagRepository {
        return TagRepository().also { it.api = api }
    }
}