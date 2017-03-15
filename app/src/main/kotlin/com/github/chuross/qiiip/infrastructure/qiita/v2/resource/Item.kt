package com.github.chuross.qiiip.infrastructure.qiita.v2.resource

import com.google.gson.annotations.SerializedName
import java.util.*

data class Item(
        var id: String?,
        var title: String?,
        var url: String?,
        var private: Boolean?,
        var body: String?,
        @SerializedName("rendered_body")
        var renderedBody: String?,
        var user: User?,
        var tags: MutableList<Tag>?,
        var createdAt: Date?,
        var updatedAt: Date?
)