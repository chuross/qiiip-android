package com.github.chuross.qiiip.ui.viewmodel.activity

import android.content.Context
import android.net.Uri
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import jp.keita.kagurazaka.rxproperty.RxProperty

class LoginActivityViewModel(override val context: Context, val uri: Uri?) : ActivityViewModel() {

    val isLoginSuccess: RxProperty<Boolean> = RxProperty()
    val error: RxProperty<Throwable> = RxProperty()

    fun login() {
        uri?.getQueryParameter("code")?.let {
            application.useCases.authorizeAccount(it).compose {
                it.bindToLifecycle(this@LoginActivityViewModel)
                        .subscribeOn(application.serialScheduler)
                        .observeOn(application.mainThreadScheduler)
            }.apply {
                disposables.add(this)
            }.exec({
                isLoginSuccess.set(true)
            }, {
                error.set(it)
            })
        } ?: error.set(IllegalArgumentException())
    }
}