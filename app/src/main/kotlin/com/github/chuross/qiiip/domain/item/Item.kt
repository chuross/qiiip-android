package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.AbstractEntity

class Item(identity: ItemIdentity) : AbstractEntity<ItemIdentity, Item>(identity) {

    var metaInfo: ItemMetaInfo? = null
}