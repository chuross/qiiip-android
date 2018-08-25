package com.github.chuross.qiiip.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.ui.view.activity.ScreenActivity
import com.github.chuross.qiiip.ui.viewmodel.fragment.FragmentViewModel
import com.trello.rxlifecycle2.android.FragmentEvent

abstract class BaseFragment<B : ViewDataBinding, VM : FragmentViewModel> : Fragment() {

    abstract val layoutResourceId: Int
    val application: Application get() = Application.from(requireContext())
    val screenActivity: ScreenActivity get() = activity as ScreenActivity
    lateinit var binding: B
    lateinit var viewModel: VM

    abstract fun onCreateViewModel(context: Context): VM

    fun FragmentManager.renderIfNeeded(container: ViewGroup?, fragment: Fragment) {
        container?.let {
            if (childFragmentManager.findFragmentById(it.id) != null) return

            childFragmentManager.beginTransaction().replace(it.id, fragment).commitNow()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = onCreateViewModel(requireContext())
        viewModel.notifyLifecycleEvent(FragmentEvent.CREATE)
        viewModel.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewModel.notifyLifecycleEvent(FragmentEvent.CREATE_VIEW)
        binding = DataBindingUtil.inflate(inflater, layoutResourceId, container, false)
        return binding.root
    }

    override fun onStart() {
        super.onStart()
        viewModel.notifyLifecycleEvent(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        viewModel.notifyLifecycleEvent(FragmentEvent.RESUME)
    }

    override fun onPause() {
        viewModel.notifyLifecycleEvent(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        viewModel.notifyLifecycleEvent(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        viewModel.notifyLifecycleEvent(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        viewModel.notifyLifecycleEvent(FragmentEvent.DESTROY)
        viewModel.destroy()
        super.onDestroy()
    }

    override fun onDetach() {
        viewModel.notifyLifecycleEvent(FragmentEvent.DETACH)
        super.onDetach()
    }
}