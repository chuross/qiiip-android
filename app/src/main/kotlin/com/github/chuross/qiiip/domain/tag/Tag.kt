package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.AbstractEntity

class Tag(identity: TagIdentity) : AbstractEntity<TagIdentity, Tag>(identity) {

    val iconUrl: String? get() = metaInfo?.iconUrl
    val itemCount: Long get() = metaInfo?.itemCount ?: 0
    val followersCount: Long get() = metaInfo?.followersCount ?: 0
    var metaInfo: TagMetaInfo? = null
}
