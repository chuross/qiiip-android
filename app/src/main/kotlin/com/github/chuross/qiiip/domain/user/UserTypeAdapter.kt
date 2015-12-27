package com.github.chuross.qiiip.domain.user

import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.user.converter.UserConverter
import net.orange_box.storebox.adapters.base.BaseStringTypeAdapter

class UserTypeAdapter : BaseStringTypeAdapter<User>() {

    override fun adaptFromPreferences(value: String?): User? = value?.let { UserConverter.convertToModel(Application.GSON.fromJson(it, com.github.chuross.qiiip.infrastructure.qiita.resource.User::class.java)) }

    override fun adaptForPreferences(value: User?): String? = value?.let { Application.GSON.toJson(UserConverter.convertToResource(it)) }

}