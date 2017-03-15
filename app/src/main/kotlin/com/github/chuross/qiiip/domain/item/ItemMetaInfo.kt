package com.github.chuross.qiiip.domain.item

import com.github.chuross.qiiip.domain.ValueObject
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.user.User
import java.util.*

data class ItemMetaInfo(
        val title: String,
        val url: String,
        val private: Boolean,
        val body: String?,
        val user: User,
        val tags: List<Tag>,
        val createdAt: Date?,
        val updatedAt: Date?
) : ValueObject