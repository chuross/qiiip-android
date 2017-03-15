package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.tag.TagResourceConverter
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserResourceConverter
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Item as Resource

object ItemResourceConverter {

    fun toModel(resource: Resource): Item = Item(ItemIdentity(resource.id!!)).apply {
        metaInfo = ItemMetaInfo(
                title = resource.title ?: "",
                url = resource.url ?: "",
                private = resource.private ?: false,
                body = resource.body,
                user = resource.user?.let { UserResourceConverter.toModel(it) } ?: User.empty,
                tags =  resource.tags?.let { TagResourceConverter.toModels(it) } ?: listOf(),
                createdAt = resource.createdAt,
                updatedAt = resource.updatedAt
        )
    }
}