package com.github.chuross.qiiip.infrastructure.qiita.v2.resource

import com.google.gson.annotations.SerializedName

data class Tag(
        var id: String?,
        var name: String?,
        @SerializedName("icon_url")
        var iconUrl: String?,
        @SerializedName("items_count")
        var itemsCount: Long?,
        @SerializedName("followers_count")
        var followersCount: Long?
)
