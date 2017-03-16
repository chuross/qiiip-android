package com.github.chuross.qiiip.infrastructure.qiiip

import com.github.chuross.qiiip.Settings
import okhttp3.HttpUrl

object Configs {

    fun getQiitaAuthUrl(state: String): String {
        return HttpUrl.parse(Settings.qiita.apiUrl).newBuilder()
                .addQueryParameter("scope", "read_qiita write_qiita")
                .addQueryParameter("state", state)
                .addQueryParameter("clientId", Settings.qiita.clientId)
                .toString()
    }
}