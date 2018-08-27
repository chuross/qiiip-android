package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.AbstractEntity

class User(identity: UserIdentity) : AbstractEntity<UserIdentity, User>(identity) {

    companion object {
        val empty: User = User(UserIdentity("")).also {
            it.metaInfo = UserMetaInfo("名無し", null, null)
        }
    }

    val name: String? get() = metaInfo?.name
    val description: String? get() = metaInfo?.description
    val profileImageUrl: String? get() = metaInfo?.profileImageUrl
    var metaInfo: UserMetaInfo? = null
}