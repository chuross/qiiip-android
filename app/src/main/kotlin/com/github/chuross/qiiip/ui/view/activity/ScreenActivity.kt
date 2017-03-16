package com.github.chuross.qiiip.ui.view.activity

import android.os.Bundle
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.ScreenChangeEvent
import com.github.chuross.qiiip.application.screen.ItemListScreen
import com.github.chuross.qiiip.databinding.ActivityScreenBinding
import com.github.chuross.qiiip.ui.viewmodel.ScreenActivityViewModel
import com.michaelflisar.rxbus2.RxBus
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

class ScreenActivity : BaseActivity<ActivityScreenBinding>() {

    private var viewModel: ScreenActivityViewModel? = null
    override val layoutResourceId: Int? = R.layout.activity_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ScreenActivityViewModel(applicationContext)
        viewModel?.let { viewModel ->
            bindViewModel(viewModel)
            RxBusBuilder.create(ScreenChangeEvent::class.java).build()
                    .bindUntilEvent(viewModel, ActivityEvent.DESTROY)
                    .filter { viewModel.isDifferentScreen(this@ScreenActivity, it.screen) }
                    .subscribe { event ->
                        binding?.screenContainer?.let {
                            supportFragmentManager.beginTransaction().apply {
                                replace(it.id, event.screen.fragment)
                                supportFragmentManager.findFragmentById(it.id)?.let {
                                    addToBackStack(event.screen.identity)
                                }
                            }.commitNow()
                        }
                    }
                    .apply { viewModel.disposables.add(this) }
        }
        RxBus.get().send(ScreenChangeEvent(ItemListScreen()))
    }
}
