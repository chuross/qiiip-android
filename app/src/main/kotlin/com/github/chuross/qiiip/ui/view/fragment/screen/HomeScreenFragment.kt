package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.github.chuross.morirouter.annotation.RouterPath
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.AuthenticationChangeEvent
import com.github.chuross.qiiip.databinding.FragmentHomeScreenBinding
import com.github.chuross.qiiip.ui.adapter.FragmentPagerAdapter
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.view.fragment.component.FeedListFragment
import com.github.chuross.qiiip.ui.view.fragment.component.ItemListFragment
import com.github.chuross.qiiip.ui.view.fragment.component.StockItemListFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.HomeScreenFragmentViewModel
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.HomeScreenFragmentViewModelBuilder
import com.michaelflisar.rxbus2.RxBusBuilder
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent

@RouterPath(name = "home")
class HomeScreenFragment : BaseFragment<FragmentHomeScreenBinding>() {

    private lateinit var viewModel: HomeScreenFragmentViewModel
    override val layoutResourceId: Int = R.layout.fragment_home_screen
    private val tabItems: List<Pair<String, (() -> Fragment)>>
        get() = if (qiiipApplication.isAuthorized) {
            listOf(
                    "全ての投稿" to { ItemListFragment() },
                    "フィード" to { FeedListFragment() },
                    "ストック" to { StockItemListFragment() }
            )
        } else {
            listOf(
                    "全ての投稿" to { ItemListFragment() }
            )
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = HomeScreenFragmentViewModelBuilder(requireContext()).build(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.toolbar.setNavigationOnClickListener {
            screenActivity.openDrawer()
        }

        RxBusBuilder.create(AuthenticationChangeEvent::class.java).build()
                .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribe { rebuildTabs() }
                .also { viewModel.disposables.add(it) }

        rebuildTabs()
    }

    private fun rebuildTabs() {
        binding.viewpager.also {
            it.adapter = FragmentPagerAdapter(childFragmentManager, tabItems)
            binding.tab.setupWithViewPager(it)
            it.setCurrentItem(viewModel.defaultTabIndex, false)
        }
    }
}