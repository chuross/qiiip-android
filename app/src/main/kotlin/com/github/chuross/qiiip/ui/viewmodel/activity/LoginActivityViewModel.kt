package com.github.chuross.qiiip.ui.viewmodel.activity

import android.content.Context
import android.net.Uri
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.ui.viewmodel.BaseViewModel
import com.github.chuross.viewmodelargs.annotation.Argument
import com.github.chuross.viewmodelargs.annotation.ViewModelWithArgs
import jp.keita.kagurazaka.rxproperty.RxProperty

@ViewModelWithArgs
class LoginActivityViewModel : BaseViewModel() {

    @Argument
    lateinit var context: Context

    @Argument(required = false)
    var uri: Uri? = null

    val isLoginSuccess: RxProperty<Boolean> = RxProperty()
    val error: RxProperty<Throwable> = RxProperty()
    private val application: Application get() = Application.from(context)

    fun login() {
        uri?.getQueryParameter("code")?.let {
            application.useCases.authorizeAccount(it).compose {
                it.subscribeOn(application.serialScheduler)
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