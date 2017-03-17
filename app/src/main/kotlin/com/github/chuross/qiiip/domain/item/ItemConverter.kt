package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.tag.TagConverter
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserConverter
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Item as Resource

object ItemConverter {

    fun toModels(resources: List<Resource>): List<Item> = resources.map { toModel(it) }

    fun toModel(resource: Resource): Item = Item(ItemIdentity(resource.id!!)).apply {
        metaInfo = ItemMetaInfo(
                title = resource.title ?: "",
                url = resource.url ?: "",
                private = resource.private ?: false,
                body = resource.body,
                user = resource.user?.let { UserConverter.toModel(it) } ?: User.empty,
                tags =  resource.tags?.let { TagConverter.toModels(it) } ?: listOf(),
                createdAt = resource.createdAt,
                updatedAt = resource.updatedAt
        )
    }
}