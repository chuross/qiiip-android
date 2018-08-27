package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.Tag as Resource

object TagConverter {

    fun toModels(resources: List<Resource>) = resources.map { toModel(it) }

    fun toModel(resource: Resource): Tag = Tag(TagIdentity(resource.id ?: resource.name!!)).also {
        it.metaInfo = TagMetaInfo(
                iconUrl = resource.iconUrl,
                itemCount = resource.itemsCount ?: 0,
                followersCount = resource.followersCount ?: 0
        )
    }
}