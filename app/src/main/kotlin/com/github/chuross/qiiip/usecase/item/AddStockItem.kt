package com.github.chuross.qiiip.usecase.item

import com.github.chuross.qiiip.domain.item.ItemIdentity
import com.github.chuross.qiiip.domain.item.StockService
import com.github.chuross.qiiip.usecase.BaseRxUseCase
import io.reactivex.Single
import javax.inject.Inject

class AddStockItem(val itemIdentity: ItemIdentity) : BaseRxUseCase<Boolean>() {

    @Inject lateinit var stockService: StockService

    override fun source(): Single<Boolean> {
        return stockService.addStockItem(itemIdentity).map { true }
    }
}