package com.github.chuross.qiiip.ui.view.fragment.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentTagScreenBinding
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.view.fragment.component.TagItemListFragmentBuilder
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.TagScreenFragmentViewModel
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs

@FragmentWithArgs
class TagScreenFragment : BaseFragment<FragmentTagScreenBinding, TagScreenFragmentViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_tag_screen
    @Arg
    lateinit var tag: Tag

    override fun onCreateViewModel(context: Context): TagScreenFragmentViewModel {
        return TagScreenFragmentViewModel(context, tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        FragmentArgs.inject(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.toolbar?.setNavigationOnClickListener { application.popScreen() }

        childFragmentManager.renderIfNeeded(binding.container, TagItemListFragmentBuilder(tag).build())
    }
}