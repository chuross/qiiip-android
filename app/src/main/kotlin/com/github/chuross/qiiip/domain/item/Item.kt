package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.AbstractEntity
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.user.User
import java.util.*

class Item(identity: ItemIdentity) : AbstractEntity<ItemIdentity, Item>(identity) {

    val title: String? get() = metaInfo?.title
    val tags: List<Tag> get() = metaInfo?.tags ?: listOf()
    val tagLabel: String? get() = tags.joinToString(separator = ", ", transform = { it.identity.value })
    val user: User get() = metaInfo?.user ?: User.empty
    val createdAt: Date? get() = metaInfo?.createdAt

    var metaInfo: ItemMetaInfo? = null
}