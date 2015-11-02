package com.github.chuross.qiiip.domain.user.converter

import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.infrastructure.qiita.resource.User as ResourceUser

class UserConverter {

    private constructor()

    companion object {

        fun convertToModel(resource: ResourceUser): User {
            val user: User = User(UserIdentity(resource.id!!))
            user.metaInfo = UserMetaConverter.convertToModel(resource)
            return user
        }
    }

}