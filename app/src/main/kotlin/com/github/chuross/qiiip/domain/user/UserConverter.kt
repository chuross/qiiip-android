package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.User as Resource

object UserConverter {

    fun toModel(resource: Resource): User = User(UserIdentity(resource.id!!)).apply {
        metaInfo = UserMetaInfo(
                name = resource.name ?: "名無し",
                description = resource.description,
                profileImageUrl = resource.profileImageUrl
        )
    }

    fun toResource(model: User): Resource = Resource(
            id = model.identity.value,
            name = model.name,
            description = model.description,
            profileImageUrl = model.profileImageUrl
    )
}