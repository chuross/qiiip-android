package com.github.chuross.qiiip.ui.view.fragment.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.AuthenticationChangeEvent
import com.github.chuross.qiiip.databinding.FragmentHomeScreenBinding
import com.github.chuross.qiiip.ui.adapter.FragmentPagerAdapter
import com.github.chuross.qiiip.ui.view.activity.ScreenActivity
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.HomeScreenFragmentViewModel
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding, HomeScreenFragmentViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_home_screen

    override fun onCreateViewModel(context: Context): HomeScreenFragmentViewModel {
        return HomeScreenFragmentViewModel(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.toolbar.setNavigationOnClickListener {
            (activity as? ScreenActivity)?.let(ScreenActivity::openDrawer)
        }

        RxBusBuilder.create(AuthenticationChangeEvent::class.java).build()
                .bindUntilEvent(viewModel, FragmentEvent.DESTROY_VIEW)
                .subscribe { rebuildTabs() }
                .apply { viewModel.disposables.add(this) }

        rebuildTabs()
    }

    private fun rebuildTabs() {
        binding.viewpager.apply {
            adapter = FragmentPagerAdapter(childFragmentManager, viewModel.tabItems)
            binding.tab.setupWithViewPager(this)
            setCurrentItem(viewModel.defaultTabIndex, false)
        }
    }
}