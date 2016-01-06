package com.github.chuross.qiiip.application

import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserTypeAdapter
import net.orange_box.storebox.annotations.method.KeyByResource
import net.orange_box.storebox.annotations.method.RemoveMethod
import net.orange_box.storebox.annotations.method.TypeAdapter
import net.orange_box.storebox.annotations.option.SaveOption
import net.orange_box.storebox.enums.SaveMode

interface SharedPreferences {

    @KeyByResource(R.string.pref_key_authenticated_user)
    @TypeAdapter(UserTypeAdapter::class)
    fun getAuthenticationUser(): User?

    @SaveOption(SaveMode.COMMIT)
    @KeyByResource(R.string.pref_key_authenticated_user)
    @TypeAdapter(UserTypeAdapter::class)
    fun setAuthenticatedUser(user: User): SharedPreferences

    @RemoveMethod
    @SaveOption(SaveMode.COMMIT)
    @KeyByResource(R.string.pref_key_authenticated_user)
    @TypeAdapter(UserTypeAdapter::class)
    fun removeAuthenticatedUser(): SharedPreferences

    @SaveOption(SaveMode.COMMIT)
    @KeyByResource(R.string.pref_key_access_token)
    fun getAccessToken(): String?

    @SaveOption(SaveMode.COMMIT)
    @KeyByResource(R.string.pref_key_access_token)
    fun setAccessToken(token: String): SharedPreferences

    @RemoveMethod
    @SaveOption(SaveMode.COMMIT)
    @KeyByResource(R.string.pref_key_access_token)
    fun removeAccessToken(): SharedPreferences
}