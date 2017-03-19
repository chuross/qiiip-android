package com.github.chuross.qiiip.ui.view.activity

import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.ScreenChangeEvent
import com.github.chuross.qiiip.application.event.ScreenPopEvent
import com.github.chuross.qiiip.application.screen.HomeScreen
import com.github.chuross.qiiip.application.screen.Screen
import com.github.chuross.qiiip.databinding.ActivityScreenBinding
import com.github.chuross.qiiip.ui.viewmodel.activity.ScreenActivityViewModel
import com.michaelflisar.rxbus2.RxBus
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent


class ScreenActivity : BaseActivity<ActivityScreenBinding>() {

    override val layoutResourceId: Int? = R.layout.activity_screen
    private lateinit var viewModel: ScreenActivityViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // ステータスバー透過用処理
        findViewById(android.R.id.content).systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE

        viewModel = ScreenActivityViewModel(applicationContext)
        bindViewModel(viewModel)

        RxBusBuilder.create(ScreenChangeEvent::class.java).build()
                .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                .subscribe { changeScreen(it.screen) }
                .apply { viewModel.disposables.add(this) }

        RxBusBuilder.create(ScreenPopEvent::class.java).build()
                .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                .subscribe { supportFragmentManager.popBackStack() }
                .apply { viewModel.disposables.add(this) }

        RxBus.get().send(ScreenChangeEvent(HomeScreen()))
    }

    private fun changeScreen(screen: Screen) {
        binding?.screenContainer?.let {
            supportFragmentManager.beginTransaction().apply {
                replace(it.id, screen.fragment)
                supportFragmentManager.findFragmentById(it.id)?.let {
                    addToBackStack(screen.identity)
                }
            }.commit()
            supportFragmentManager.executePendingTransactions()
            viewModel.currentScreen.set(screen)
        }
    }
}
