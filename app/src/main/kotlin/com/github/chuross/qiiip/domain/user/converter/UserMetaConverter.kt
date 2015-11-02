package com.github.chuross.qiiip.domain.user.converter

import com.github.chuross.qiiip.domain.user.UserMetaInfo
import com.github.chuross.qiiip.infrastructure.qiita.resource.User as ResourceUser

class UserMetaConverter {

    private constructor()

    companion object {
        fun convertToModel(resource: ResourceUser): UserMetaInfo {
            return UserMetaInfo(
                    resource.name!!,
                    resource.description,
                    resource.profileImageUrl,
                    resource.websiteUrl
            )
        }
    }
}