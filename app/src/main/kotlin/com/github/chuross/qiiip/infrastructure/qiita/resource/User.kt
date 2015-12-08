package com.github.chuross.qiiip.infrastructure.qiita.resource

import com.google.gson.annotations.SerializedName

data class User(
        var id: String?,
        var name: String?,
        var description: String?,
        @SerializedName("profile_image_url")
        var profileImageUrl: String?,
        var websiteUrl: String
)