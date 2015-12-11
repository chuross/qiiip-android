package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.domain.common.AbstractEntity

class User(identity: UserIdentity) : AbstractEntity<UserIdentity, User>(identity) {

    var metaInfo: UserMetaInfo? = null

}