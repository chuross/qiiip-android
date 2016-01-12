package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.domain.user.UserIdentity
import com.github.chuross.qiiip.infrastructure.android.AndroidUtils
import com.github.chuross.qiiip.ui.fragment.PagerListFragment
import com.github.chuross.qiiip.ui.fragment.presenter.UserScreenFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.UserScreenFragmentTemplate
import com.github.chuross.qiiip.ui.widget.adapter.ItemArrayAdapter
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

class UserScreenFragment : PagerListFragment<UserScreenFragmentPresenter, UserScreenFragmentTemplate, Item>(), ScreenFragment {
    companion object {
        private val ARGUMENT_KEY_USER = "argument_key_user"

        fun create(user: User): UserScreenFragment {
            return UserScreenFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARGUMENT_KEY_USER, user)
                }
            }
        }
    }

    val userIdentity: UserIdentity by lazy { (arguments.getSerializable(ARGUMENT_KEY_USER) as User).identity }
    var statusBarColor: Int? = null
    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.USER
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = UserScreenFragment::class.java.name
    override val adapter: RecyclerViewCollectionAdapter<*, *> by lazy { ItemArrayAdapter(activity) }
    override val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(activity) }

    override fun createPresenter(): UserScreenFragmentPresenter = UserScreenFragmentPresenter(this)

    override fun createTemplate(parent: ViewGroup, p1: Bundle?): UserScreenFragmentTemplate = UserScreenFragmentTemplate(activity, parent)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        val user = arguments.getSerializable(ARGUMENT_KEY_USER) as User

        screenActivity.setUpToolbar(template.toolbar)
        template.toolbar.title = user.identity.value
        template.paletteCallback = { palette ->
            palette.darkVibrantSwatch?.let { darkVibrantSwatch ->
                statusBarColor = darkVibrantSwatch.rgb
                AndroidUtils.setStatusBarColor(activity, statusBarColor!!)
            }
        }
        template.apply(user)

        (adapter as ItemArrayAdapter).clickListener = { view, item ->
            screenActivity.launchScreen(ItemDetailScreenFragment.create(item))
        }
    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (hidden) {
            AndroidUtils.setStatusBarColor(activity, resources.getColor(R.color.primary_dark, null))
        } else {
            statusBarColor?.let { AndroidUtils.setStatusBarColor(activity, it) }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        AndroidUtils.setStatusBarColor(activity, resources.getColor(R.color.primary_dark, null))
    }
}