package com.github.chuross.qiiip.ui.view.activity

import android.databinding.DataBindingUtil
import android.databinding.ViewDataBinding
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.github.chuross.qiiip.ui.viewmodel.ActivityViewModel
import com.trello.rxlifecycle2.android.ActivityEvent

abstract class BaseActivity<BINDING: ViewDataBinding>: AppCompatActivity() {

    abstract val layoutResourceId: Int?
    var binding: BINDING? = null
    private var boundViewModel: ActivityViewModel? = null

    fun bindViewModel(viewModel: ActivityViewModel) {
        boundViewModel = viewModel
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        layoutResourceId?.let {
            binding = DataBindingUtil.setContentView(this, it)
        }
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.START)
    }

    override fun onResume() {
        super.onResume()
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.RESUME)
    }

    override fun onPause() {
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        boundViewModel?.notifyLifecycleEvent(ActivityEvent.DESTROY)
        boundViewModel?.destroy()
        super.onDestroy()
    }
}