package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import com.github.chuross.morirouter.MoriBinder
import com.github.chuross.morirouter.UserItemListFragmentBuilder
import com.github.chuross.morirouter.annotation.Argument
import com.github.chuross.morirouter.annotation.RouterPath
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentUserDetailBinding
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.transition.SimpleImageTransitionFactory
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment

@RouterPath(
        name = "user_detail",
        sharedEnterTransitionFactory = SimpleImageTransitionFactory::class,
        sharedExitTransitionFactory = SimpleImageTransitionFactory::class
)
class UserDetailScreenFragment : BaseFragment<FragmentUserDetailBinding>() {

    @Argument
    lateinit var user: User

    override val layoutResourceId: Int = R.layout.fragment_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        MoriBinder.bindElement(this, R.id.profile_img)

        binding.toolbar.setNavigationOnClickListener { screenActivity.router.pop() }
        binding.user = user
        binding.executePendingBindings()

        childFragmentManager.renderIfNeeded(binding.container, UserItemListFragmentBuilder(user).build())
    }
}