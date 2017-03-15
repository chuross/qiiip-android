package com.github.chuross.qiiip.ui.view.fragment

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.chuross.qiiip.ui.viewmodel.FragmentViewModel
import com.trello.rxlifecycle2.android.FragmentEvent

class BaseFragment : Fragment() {

    private var boundViewModel: FragmentViewModel? = null

    fun bindViewModel(viewModel: FragmentViewModel) {
        boundViewModel = viewModel
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.CREATE)
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.CREATE_VIEW)
        return null
    }

    override fun onStart() {
        super.onStart()
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.RESUME)
    }

    override fun onPause() {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    override fun onDetach() {
        boundViewModel?.notifyLifecycleEvent(FragmentEvent.DETACH)
        super.onDetach()
    }
}