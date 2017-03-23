package com.github.chuross.qiiip.application.preference.serializer

import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserConverter
import com.google.gson.Gson
import com.rejasupotaro.android.kvs.serializers.PrefsSerializer
import com.github.chuross.qiiip.infrastructure.qiita.v2.resource.User as Resource

class UserSerializer : PrefsSerializer<User?, String?> {

    private val gson: Gson = Gson()

    override fun serialize(src: User?): String? {
        return src?.let { gson.toJson(UserConverter.toResource(it)) }
    }

    override fun deserialize(src: String?): User? {
        return if (src?.isNotBlank() ?: false) UserConverter.toModel(gson.fromJson(src, Resource::class.java)) else null
    }
}