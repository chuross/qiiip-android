package com.github.chuross.qiiip.infrastructure.extension

import com.github.chuross.qiiip.Settings
import okhttp3.HttpUrl

fun Settings.getQiitaAuthUrl(state: String): String {
    return HttpUrl.parse(Settings.qiita.apiUrl).newBuilder()
            .addQueryParameter("scope", "read_qiita write_qiita")
            .addQueryParameter("state", state)
            .addQueryParameter("clientId", Settings.qiita.clientId)
            .toString()
}