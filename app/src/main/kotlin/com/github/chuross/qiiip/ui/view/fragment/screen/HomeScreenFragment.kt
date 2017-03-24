package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentHomeScreenBinding
import com.github.chuross.qiiip.ui.adapter.FragmentPagerAdapter
import com.github.chuross.qiiip.ui.view.activity.ScreenActivity
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.HomeScreenFragmentViewModel

class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    override val layoutResourceId: Int = R.layout.fragment_home_screen
    private lateinit var viewModel: HomeScreenFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = HomeScreenFragmentViewModel(context)
        bindViewModel(viewModel)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.toolbar?.setNavigationOnClickListener {
            (activity as? ScreenActivity)?.let(ScreenActivity::openDrawer)
        }
        binding?.viewpager?.apply {
            adapter = FragmentPagerAdapter(childFragmentManager, viewModel.tabItems)
            binding?.tab?.let { it.setupWithViewPager(this) }
            setCurrentItem(viewModel.defaultTabIndex, false)
        }
    }
}