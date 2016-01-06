package com.github.chuross.qiiip.ui.activity.presenter

import android.widget.Toast
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.infrastructure.qiita.QiitaV2Api
import com.github.chuross.qiiip.infrastructure.qiita.request.TokenRequest
import com.github.chuross.qiiip.ui.activity.LoginActivity
import com.github.chuross.qiiip.ui.activity.template.LoginActivityTemplate
import rx.Observable
import javax.inject.Inject

class LoginActivityPresenter(activity: LoginActivity) : ActivityPresenter<LoginActivity, LoginActivityTemplate>(activity) {

    @Inject
    lateinit var api: QiitaV2Api

    override fun createTemplate(p0: LoginActivity?): LoginActivityTemplate = LoginActivityTemplate(view)

    fun request(): Observable<User> {
        val application = Application.from(view)

        return view.intent?.data?.getQueryParameter("code")?.let { code ->
            api.login(TokenRequest(application.requestContext.clientId, application.requestContext.clientSecret, code))
                    .filter { token -> !token.value.isNullOrBlank() }
                    .map { token ->
                        application.preferences.refreshToken(token.value.orEmpty())
                    }
                    .flatMap {
                        application.userRepository.authenticatedUser()
                    }
                    .filter { user ->
                        application.preferences.putAuthenticatedUser(user) != null
                    }
                    .doOnError {
                        // 失敗したらトークンを破棄する
                        application.preferences.removeAccessToken()
                    }
        } ?: Observable.error(IllegalArgumentException())
    }

    fun onRequestSuccess() {
        Toast.makeText(view, "ログインが完了しました", Toast.LENGTH_LONG).show()
        view.finish()
    }

    fun onRequestFailed(tr: Throwable) {
        Toast.makeText(view, "ログインに失敗しました", Toast.LENGTH_LONG).show()
        view.finish()
    }
}