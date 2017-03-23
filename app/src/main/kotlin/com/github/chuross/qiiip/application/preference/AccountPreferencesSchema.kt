package com.github.chuross.qiiip.application.preference

import com.github.chuross.qiiip.application.preference.serializer.UserSerializer
import com.rejasupotaro.android.kvs.annotations.Key
import com.rejasupotaro.android.kvs.annotations.Table

@Table(name = "account", builder = AccountPreferencesBuilder::class)
class AccountPreferencesSchema {

    @Key(name = "user", serializer = UserSerializer::class) var user: String? = null
    @Key(name = "token") var token: String? = null
}