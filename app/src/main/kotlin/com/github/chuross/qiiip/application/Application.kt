package com.github.chuross.qiiip.application

import android.app.Activity
import android.net.Uri
import com.github.chuross.chuross.qiiip.BuildConfig
import com.github.chuross.qiiip.domain.item.ItemRepository
import com.github.chuross.qiiip.domain.tag.TagRepository
import com.github.chuross.qiiip.domain.user.UserRepository
import com.github.chuross.qiiip.infrastructure.encryption.AesCryptUtils
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import com.github.chuross.qiiip.infrastructure.qiita.RequestContext
import com.google.gson.Gson
import com.google.gson.GsonBuilder

open class Application : android.app.Application() {

    companion object {
        val GSON: Gson = GsonBuilder().apply {
            setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        }.create()

        fun from(activity: Activity): Application = activity.application as Application
    }

    val component: ApplicationComponent
    val userRepository: UserRepository
        get() = UserRepository().apply { component.inject(this) }
    val itemRepository: ItemRepository
        get() = ItemRepository().apply { component.inject(this) }
    val tagRepository: TagRepository
        get() = TagRepository().apply { component.inject(this) }
    val requestContext by lazy {
        val clientId = AesCryptUtils.decrypt(BuildConfig.CLIENT_ID, BuildConfig.KEY_STR)
        val clientSecret = AesCryptUtils.decrypt(BuildConfig.CLIENT_SECRET, BuildConfig.KEY_STR)
        RequestContext(Uri.parse(QiitaV2Api.BASE_URL), clientId, clientSecret)
    }

    val preferences by lazy { ApplicationPreferences(this) }

    init {
        component = DaggerApplicationComponent.builder().qiipModule(newQiipModule()).build()
    }

    open protected fun newQiipModule(): QiipModule = QiipModule(this)
}