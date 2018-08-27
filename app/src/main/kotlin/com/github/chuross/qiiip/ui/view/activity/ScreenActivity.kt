package com.github.chuross.qiiip.ui.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Gravity
import androidx.transition.Fade
import com.github.chuross.morirouter.MoriRouter
import com.github.chuross.morirouter.core.MoriRouterOptions
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.application.event.AuthenticationChangeEvent
import com.github.chuross.qiiip.application.event.ScreenPopEvent
import com.github.chuross.qiiip.databinding.ActivityScreenBinding
import com.github.chuross.qiiip.databinding.ViewDrawerHeaderBinding
import com.github.chuross.qiiip.infrastructure.qiita.v2.QiitaV2Api
import com.github.chuross.qiiip.ui.viewmodel.activity.ScreenActivityViewModel
import com.github.chuross.qiiip.ui.viewmodel.activity.ScreenActivityViewModelBuilder
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent


class ScreenActivity : BaseActivity<ActivityScreenBinding>() {

    val router: MoriRouter by lazy {
        val option = MoriRouterOptions.Builder(R.id.screen_container)
                .setEnterTransitionFactory { Fade() }
                .setExitTransitionFactory { Fade() }
                .build()
        MoriRouter(supportFragmentManager, option)
    }
    override val layoutResourceId: Int? = R.layout.activity_screen
    private lateinit var viewModel: ScreenActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ScreenActivityViewModelBuilder().build(this)

        val headerBinding = ViewDrawerHeaderBinding.bind(binding?.navigation?.getHeaderView(0)!!)
        headerBinding.user = qiiipApplication.authorizedUser
        headerBinding.loginButton.setOnClickListener {
            startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(QiitaV2Api.getQiitaAuthUrl(Settings.qiita.apiUrl, Settings.qiita.clientId, ""))))
        }

        binding?.navigation?.setNavigationItemSelectedListener {
            closeDrawer()
            when (it.itemId) {
                R.id.menu_my_items -> Application.from(this).authorizedUser?.let {
                    router.userDetail(it).launch()
                    true
                } ?: false
                else -> false
            }
        }

        RxBusBuilder.create(ScreenPopEvent::class.java).build()
                .bindUntilEvent(this, ActivityEvent.DESTROY)
                .subscribe { supportFragmentManager.popBackStack() }
                .also { viewModel.disposables.add(it) }

        RxBusBuilder.create(AuthenticationChangeEvent::class.java).build()
                .bindUntilEvent(this, ActivityEvent.DESTROY)
                .subscribe { headerBinding.user = qiiipApplication.authorizedUser }
                .also { viewModel.disposables.add(it) }

        router.home().launch()
    }

    override fun onBackPressed() {
        if (isDrawerOpen()) closeDrawer() else super.onBackPressed()
    }

    fun isDrawerOpen(): Boolean = binding?.drawerLayout?.isDrawerOpen(Gravity.START) ?: false

    fun openDrawer() {
        binding?.drawerLayout?.openDrawer(Gravity.START)
    }

    fun closeDrawer() {
        binding?.drawerLayout?.closeDrawer(Gravity.START)
    }
}
