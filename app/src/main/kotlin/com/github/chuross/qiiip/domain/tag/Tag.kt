package com.github.chuross.qiiip.domain.tag

import com.github.chuross.qiiip.domain.core.AbstractEntity

class Tag(identity: TagIdentity) : AbstractEntity<TagIdentity, Tag>(identity) {

    var metaInfo: TagMetaInfo? = null
}