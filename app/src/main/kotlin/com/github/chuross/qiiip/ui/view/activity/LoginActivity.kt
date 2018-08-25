package com.github.chuross.qiiip.ui.view.activity

import android.os.Bundle
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import com.github.chuross.qiiip.application.event.AuthenticationChangeEvent
import com.github.chuross.qiiip.ui.viewmodel.activity.LoginActivityViewModel
import com.michaelflisar.rxbus2.RxBus
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import timber.log.Timber

class LoginActivity : BaseActivity<ViewDataBinding>() {

    override val layoutResourceId: Int? = null

    private lateinit var viewModel: LoginActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("uri: ${intent.data}")

        viewModel = LoginActivityViewModel(applicationContext, intent?.data)
        bindViewModel(viewModel)

        viewModel.isLoginSuccess
                .bindToLifecycle(viewModel)
                .subscribe {
                    Toast.makeText(applicationContext, "ログイン完了しました", Toast.LENGTH_LONG).show()
                    RxBus.get().send(AuthenticationChangeEvent(true))
                    finish()
                }.apply { viewModel.disposables.add(this) }
        viewModel.error
                .bindToLifecycle(viewModel)
                .subscribe {
                    Toast.makeText(applicationContext, "ログイン失敗しました", Toast.LENGTH_LONG).show()
                    finish()
                }.apply { viewModel.disposables.add(this) }

        viewModel.login()
    }
}