package com.github.chuross.qiiip.infrastructure.qiita.resource

import java.util.*
import java.util.List

data class Item(
        var id: String?,
        var title: String?,
        var url: String?,
        var private: Boolean?,
        var body: String?,
        var renderedBody: String?,
        var user: User?,
        var tags: List<Tag>?,
        var createdAt: Date?,
        var updatedAt: Date?
)