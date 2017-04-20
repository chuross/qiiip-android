package com.github.chuross.qiiip.usecase.item

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.usecase.BaseRxUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetItems(val page: Int, val perPage: Int) : BaseRxUseCase<List<Item>>() {

    @Inject lateinit var itemRepository: ItemRepository

    override fun source(): Single<List<Item>> {
        return itemRepository.findAll(page, perPage)
    }
}