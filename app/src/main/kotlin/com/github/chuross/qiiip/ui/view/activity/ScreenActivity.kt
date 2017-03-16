package com.github.chuross.qiiip.ui.view.activity

import android.os.Bundle
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.ScreenChangeEvent
import com.github.chuross.qiiip.databinding.ActivityScreenBinding
import com.github.chuross.qiiip.ui.viewmodel.ScreenActivityViewModel
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.ActivityEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.android.schedulers.AndroidSchedulers

class ScreenActivity: BaseActivity<ActivityScreenBinding>() {

    private var viewModel: ScreenActivityViewModel? = null
    override val layoutResourceId: Int? = R.layout.activity_screen

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ScreenActivityViewModel(applicationContext)
        viewModel?.let {
            bindViewModel(it)
            RxBusBuilder.create(ScreenChangeEvent::class.java).build()
                    .bindUntilEvent(it, ActivityEvent.DESTROY)
                    .filter { viewModel?.isDifferentScreen(this@ScreenActivity, it.screen) ?: false }
                    .subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe { event ->
                        supportFragmentManager.apply {
                            binding?.let {
                                beginTransaction()
                                        .replace(it.screenContainer.id, event.screen.fragment)
                                        .commitNow()
                            }
                        }
                    }
                    .apply { it.disposables.add(this) }
        }
    }
}
