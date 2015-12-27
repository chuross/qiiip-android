package com.github.chuross.qiiip.domain.tag.converter

import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.Tag as ResourceTag

class TagConverter {

    private constructor()

    companion object {

        fun convertToModel(resource: ResourceTag): Tag = Tag(TagIdentity(resource.id ?: resource.name.orEmpty())).apply {
            metaInfo = TagMetaConverter.convertToModel(resource)
        }

        fun convertToModels(resources: Collection<ResourceTag>): List<Tag> {
            return resources.map { resource -> convertToModel(resource) }
        }
    }
}