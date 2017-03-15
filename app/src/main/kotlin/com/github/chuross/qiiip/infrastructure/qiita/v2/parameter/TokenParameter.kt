package com.github.chuross.qiiip.infrastructure.qiita.v2.parameter

import com.google.gson.annotations.SerializedName

data class TokenParameter(
        @SerializedName("client_id")
        val clientId: String,
        @SerializedName("client_secret")
        val clientSecret: String,
        val code: String
)
