package com.github.chuross.qiiip.usecase.item

import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.usecase.BaseRxUseCase
import io.reactivex.Single
import javax.inject.Inject

class GetFeedItems(val page: Int, val perPage: Int) : BaseRxUseCase<List<Item>>() {
    private val tagRequestRange = (1..3) // 300件分を上限に一気に取る

    private val tagPerPage = 100 // MAX
    @Inject lateinit var application: Application

    @Inject lateinit var tagRepository: TagRepository
    @Inject lateinit var itemRepository: ItemRepository

    override fun source(): Single<List<Item>> {
        val authenticatedUser = application.authorizedUser

        return authenticatedUser?.let {
            val requests = tagRequestRange.map {
                tagRepository.findAllByUserIdentity(authenticatedUser.identity, it, tagPerPage)
            }

            Single.merge(requests)
                    .filter { it.isNotEmpty() }
                    .map { it.map(Tag::identity) }
                    .flatMapSingle { itemRepository.findAllByTagIdentities(it, page, perPage) }
                    .collectInto(mutableListOf<Item>(), { result, items -> result.addAll(items) })
                    .map { it.toList() }
        } ?: Single.error<List<Item>>(IllegalStateException("Unauthenticated"))
    }
}