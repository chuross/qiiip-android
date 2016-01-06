package com.github.chuross.qiiip.application

import android.content.Context
import com.github.chuross.chuross.qiiip.BuildConfig
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserAuthenticationInfo
import com.github.chuross.qiiip.infrastructure.encryption.AesCryptUtils
import net.orange_box.storebox.StoreBox
import net.orange_box.storebox.enums.PreferencesType
import rx.subjects.PublishSubject

class ApplicationPreferences(val context: Context) {

    val authenticationChangeEvent = PublishSubject.create<User?>().apply { doOnNext { if (it == null) removeAccessToken() } }
    private val sharedPreferences by lazy {
        StoreBox.Builder(context, SharedPreferences::class.java).apply {
            preferencesType(PreferencesType.DEFAULT_SHARED, ApplicationPreferences::class.java.simpleName)
        }.build()
    }

    fun isAuthenticated(): Boolean = !getAuthenticatedUser()?.authenticationInfo?.token.isNullOrBlank()

    fun getAuthenticatedUser(): User? = sharedPreferences.getAuthenticationUser()?.apply {
        getAccessToken()?.let { token ->
            authenticationInfo = UserAuthenticationInfo(token)
        }
    }

    fun putAuthenticatedUser(user: User): User? {
        if (user.metaInfo == null) return null

        user.authenticationInfo?.let {
            sharedPreferences.setAuthenticatedUser(user).setAccessToken(it.token)
        } ?: sharedPreferences.setAuthenticatedUser(user)

        return sharedPreferences.getAuthenticationUser()?.let {
            authenticationChangeEvent.onNext(it)
            it
        }
    }

    fun removeAuthenticatedUser(): Boolean {
        sharedPreferences.removeAuthenticatedUser().getAuthenticationUser()
        val authenticated = isAuthenticated()
        if (authenticated) authenticationChangeEvent.onNext(null)
        return authenticated
    }

    fun getAccessToken(): String? = sharedPreferences.getAccessToken()?.let { AesCryptUtils.decrypt(it, BuildConfig.KEY_STR) }

    fun refreshToken(token: String): Boolean {
        sharedPreferences.setAccessToken(AesCryptUtils.encrypt(token, BuildConfig.KEY_STR))
        return sharedPreferences.getAccessToken().equals(token)
    }

    fun removeAccessToken(): Boolean {
        sharedPreferences.removeAccessToken()
        return sharedPreferences.getAccessToken() == null
    }

}