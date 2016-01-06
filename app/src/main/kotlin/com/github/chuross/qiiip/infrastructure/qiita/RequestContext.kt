package com.github.chuross.qiiip.infrastructure.qiita

import android.net.Uri

data class RequestContext(
        val baseUrl: Uri,
        val clientId: String,
        val clientSecret: String
)