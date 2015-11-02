package com.github.chuross.qiiip.domain.tag.converter

import com.github.chuross.qiiip.domain.tag.TagMetaInfo
import com.github.chuross.qiiip.infrastructure.qiita.resource.Tag as ResourceTag

class TagMetaConverter {

    private constructor()

    companion object {

        fun convertToModel(resource: ResourceTag): TagMetaInfo {
            return TagMetaInfo(
                    resource.name!!,
                    resource.iconUrl,
                    resource.itemsCount ?: 0,
                    resource.followersCount ?: 0
            )
        }
    }

}