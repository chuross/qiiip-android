package com.github.chuross.qiiip.usecase

import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import io.reactivex.Single
import javax.inject.Inject

class GetFeeds(val page: Int, val perPage: Int) : UseCase<List<Item>> {

    private val tagRequestRange = (1..3) // 300件分を上限に一気に取る
    private val tagPerPage = 100 // MAX

    @Inject
    lateinit var application: Application

    override fun exec(): Single<List<Item>> {
        val authenticatedUser = application.authorizedUser

        return authenticatedUser?.let {
            val requests = tagRequestRange.map {
                application.repositories.tagRepository.findAllByUserIdentity(authenticatedUser.identity, it, tagPerPage)
            }

            Single.merge(requests)
                    .filter { it.isNotEmpty() }
                    .map { it.map(Tag::identity) }
                    .flatMapSingle { application.repositories.itemRepository.findAllByTagIdentities(it, page, perPage) }
                    .collectInto(mutableListOf<Item>(), { result, items -> result.addAll(items) })
                    .map { it.toList() }
        } ?: Single.error(IllegalStateException("Unauthenticated"))
    }
}