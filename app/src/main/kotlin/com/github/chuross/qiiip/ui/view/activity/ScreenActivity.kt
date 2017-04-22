package com.github.chuross.qiiip.ui.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.application.event.AuthenticationChangeEvent
import com.github.chuross.qiiip.application.event.ScreenChangeEvent
import com.github.chuross.qiiip.application.event.ScreenPopEvent
import com.github.chuross.qiiip.application.screen.HomeScreen
import com.github.chuross.qiiip.application.screen.UserDetailScreen
import com.github.chuross.qiiip.application.screen.Screen
import com.github.chuross.qiiip.databinding.ActivityScreenBinding
import com.github.chuross.qiiip.databinding.ViewDrawerHeaderBinding
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import com.github.chuross.qiiip.ui.viewmodel.activity.ScreenActivityViewModel
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent


class ScreenActivity : BaseActivity<ActivityScreenBinding>() {

    override val layoutResourceId: Int? = R.layout.activity_screen
    private lateinit var viewModel: ScreenActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ScreenActivityViewModel(applicationContext)
        bindViewModel(viewModel)

        val headerBinding = ViewDrawerHeaderBinding.bind(binding?.navigation?.getHeaderView(0))
        headerBinding.user = viewModel.application.authorizedUser
        headerBinding.loginButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(QiitaV2Api.getQiitaAuthUrl(Settings.qiita.apiUrl, Settings.qiita.clientId, ""))))
        }

        binding?.navigation?.setNavigationItemSelectedListener {
            closeDrawer()
            when (it.itemId) {
                R.id.menu_my_items -> Application.from(this).authorizedUser?.let {
                    Application.from(this).startScreen(UserDetailScreen(it)).let { true }
                } ?: false
                else -> false
            }
        }

        RxBusBuilder.create(ScreenChangeEvent::class.java).build()
                .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                .subscribe { changeScreen(it.screen) }
                .apply { viewModel.disposables.add(this) }

        RxBusBuilder.create(ScreenPopEvent::class.java).build()
                .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                .subscribe { supportFragmentManager.popBackStack() }
                .apply { viewModel.disposables.add(this) }

        RxBusBuilder.create(AuthenticationChangeEvent::class.java).build()
                .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                .subscribe { headerBinding.user = viewModel.application.authorizedUser }
                .apply { viewModel.disposables.add(this) }

        Application.from(this).startScreen(HomeScreen())
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) closeDrawer() else super.onBackPressed()
    }

    private fun changeScreen(screen: Screen) {
        binding?.screenContainer?.let {
            supportFragmentManager.beginTransaction().apply {
                setCustomAnimations(android.R.anim.fade_in, android.R.anim.fade_out)
                replace(it.id, screen.fragment)
                supportFragmentManager.findFragmentById(it.id)?.let {
                    addToBackStack(screen.identity)
                }
            }.commit()
            supportFragmentManager.executePendingTransactions()
            viewModel.currentScreen.set(screen)
        }
    }

    fun isDrawerOpen(): Boolean = binding?.drawerLayout?.isDrawerOpen(Gravity.START) ?: false

    fun openDrawer() {
        binding?.drawerLayout?.openDrawer(Gravity.START)
    }

    fun closeDrawer() {
        binding?.drawerLayout?.closeDrawer(Gravity.START)
    }
}
