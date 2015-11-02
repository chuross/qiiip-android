package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.core.AbstractEntity
import com.github.chuross.qiiip.infrastructure.qiita.resource.Item as ResourceItem

class Item(identity: ItemIdentity) : AbstractEntity<ItemIdentity, Item>(identity) {

    var metaInfo: ItemMetaInfo? = null
}