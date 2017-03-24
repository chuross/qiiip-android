package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import io.reactivex.Single
import javax.inject.Inject

class ItemRepository {

    @Inject
    lateinit var api: QiitaV2Api

    fun findByIdentity(identity: ItemIdentity): Single<Item> {
        return api.getItemById(identity.value).map { ItemConverter.toModel(it) }
    }

    fun findAll(page: Int, perPage: Int): Single<List<Item>> {
        return api.getItems(page, perPage).map { ItemConverter.toModels(it) }
    }

    fun findAllByKeyword(query: String, page: Int, perPage: Int): Single<List<Item>> {
        return api.getItemsByKeyword(query, page, perPage).map { ItemConverter.toModels(it) }
    }

    fun findAllByTagIdentity(tagIdentity: TagIdentity, page: Int, perPage: Int): Single<List<Item>> {
        return api.getItemsByTagId(tagIdentity.value, page, perPage).map { ItemConverter.toModels(it) }
    }

    fun findAllByTagIdentities(tagIdentities: List<TagIdentity>, page: Int, perPage: Int): Single<List<Item>> {
        return api.getItemsByKeyword(tagIdentities.joinToString(separator = " OR ", transform = { "tag:${it.value}" }), page, perPage)
                .map { ItemConverter.toModels(it) }
    }
}