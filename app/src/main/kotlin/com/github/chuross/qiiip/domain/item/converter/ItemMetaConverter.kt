package com.github.chuross.qiiip.domain.item.converter

import com.github.chuross.qiiip.domain.item.ItemMetaInfo
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.converter.TagConverter
import com.github.chuross.qiiip.domain.user.converter.UserConverter
import com.github.chuross.qiiip.infrastructure.qiita.resource.Item as ResourceItem

class ItemMetaConverter {

    private constructor()

    companion object {
        fun convertToModel(resource: ResourceItem): ItemMetaInfo {
            return ItemMetaInfo(
                    resource.title!!,
                    resource.url!!,
                    resource.private ?: true,
                    resource.body,
                    UserConverter.convertToModel(resource.user!!),
                    resource.tags?.let { it -> TagConverter.convertToModels(it) } ?: listOf<Tag>(),
                    resource.createdAt!!,
                    resource.updatedAt!!
            )
        }
    }

}