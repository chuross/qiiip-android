package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import io.reactivex.Single
import javax.inject.Inject

class StockService {

    @Inject
    lateinit var api: QiitaV2Api

    fun isStockItem(itemIdentity: ItemIdentity): Single<Boolean> {
        return api.isStockItem(itemIdentity.value).map { true }.singleOrError()
    }

    fun addStockItem(itemIdentity: ItemIdentity): Single<Boolean> {
        return api.addStockItem(itemIdentity.value).map { true }.singleOrError()
    }

    fun removeStockItem(itemIdentity: ItemIdentity): Single<Boolean> {
        return api.removeStockItem(itemIdentity.value).map { true }.singleOrError()
    }
}