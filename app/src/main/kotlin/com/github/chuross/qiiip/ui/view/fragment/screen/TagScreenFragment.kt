package com.github.chuross.qiiip.ui.view.fragment.screen

import android.content.Context
import android.os.Bundle
import android.view.View
import com.github.chuross.morirouter.MoriBinder
import com.github.chuross.morirouter.TagItemListFragmentBuilder
import com.github.chuross.morirouter.annotation.Argument
import com.github.chuross.morirouter.annotation.RouterPath
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentTagScreenBinding
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.TagScreenFragmentViewModel

@RouterPath(name = "tag")
class TagScreenFragment : BaseFragment<FragmentTagScreenBinding, TagScreenFragmentViewModel>() {

    override val layoutResourceId: Int = R.layout.fragment_tag_screen
    @Argument
    lateinit var tag: Tag

    override fun onCreateViewModel(context: Context): TagScreenFragmentViewModel {
        return TagScreenFragmentViewModel(context, tag)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.toolbar.setNavigationOnClickListener { screenActivity.router.pop() }

        childFragmentManager.renderIfNeeded(binding.container, TagItemListFragmentBuilder(tag).build())
    }
}