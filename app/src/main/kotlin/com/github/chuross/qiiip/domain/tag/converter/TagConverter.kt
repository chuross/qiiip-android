package com.github.chuross.qiiip.domain.tag.converter

import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.Tag as ResourceTag

class TagConverter {

    private constructor()

    companion object {

        fun convertToModel(resource: ResourceTag): Tag {
            val tag = Tag(TagIdentity(resource.id ?: resource.name.orEmpty()))
            tag.metaInfo = TagMetaConverter.convertToModel(resource)
            return tag
        }

        fun convertToModels(resources: MutableList<ResourceTag>): List<Tag> {
            return resources.map { resource -> convertToModel(resource) }
        }
    }
}