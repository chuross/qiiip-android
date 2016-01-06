package com.github.chuross.qiiip.domain.user.converter

import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.User as ResourceUser

class UserConverter {

    private constructor()

    companion object {

        fun convertToModel(resource: ResourceUser): User = User(UserIdentity(resource.id!!)).apply {
            metaInfo = UserMetaConverter.convertToModel(resource)
        }

        fun convertToResource(model: User): ResourceUser = ResourceUser(
                model.identity.value,
                model.metaInfo?.name,
                model.metaInfo?.description,
                model.metaInfo?.profileImageUrl,
                model.metaInfo?.websideUrl
        )
    }

}