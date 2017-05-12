package com.github.chuross.qiiip.ui.view.fragment.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentUserDetailBinding
import com.github.chuross.qiiip.domain.user.User
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.view.fragment.component.UserItemListFragmentBuilder
import com.github.chuross.qiiip.ui.viewmodel.SimpleFragmentViewModel
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs

@FragmentWithArgs
class UserDetailScreenFragment : BaseFragment<FragmentUserDetailBinding, SimpleFragmentViewModel>() {

    @Arg
    lateinit var user: User

    override val layoutResourceId: Int = R.layout.fragment_user_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onCreateViewModel(context: Context): SimpleFragmentViewModel {
        return SimpleFragmentViewModel(context)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbar.setNavigationOnClickListener { application.popScreen() }
        binding.user = user

        childFragmentManager.renderIfNeeded(binding.container, UserItemListFragmentBuilder(user).build())
    }
}