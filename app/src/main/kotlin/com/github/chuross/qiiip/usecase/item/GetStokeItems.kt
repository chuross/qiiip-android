package com.github.chuross.qiiip.usecase.item

import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.usecase.BaseRxUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetStokeItems(val page: Int, val perPage: Int) : BaseRxUseCase<List<Item>>() {

    @Inject lateinit var application: Application
    @Inject lateinit var itemRepository: ItemRepository

    override fun source(): Single<List<Item>> {
        if (!application.isAuthorized) {
            return Single.error(IllegalStateException("UnAuthorized"))
        }
        return application.authorizedUser?.let {
            itemRepository.getStocksByUserIdentity(it.identity, page, perPage)
        } ?: Single.error<List<Item>>(IllegalStateException("UnAuthorized"))
    }
}