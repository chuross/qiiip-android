package com.github.chuross.qiiip.ui.view.fragment.screen

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
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.TagScreenFragmentViewModelBuilder

@RouterPath(name = "tag")
class TagScreenFragment : BaseFragment<FragmentTagScreenBinding>() {

    @Argument
    lateinit var tag: Tag

    override val layoutResourceId: Int = R.layout.fragment_tag_screen
    private lateinit var viewModel: TagScreenFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)

        viewModel = TagScreenFragmentViewModelBuilder(tag).build(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.toolbar.setNavigationOnClickListener { screenActivity.router.pop() }

        childFragmentManager.renderIfNeeded(binding.container, TagItemListFragmentBuilder(tag).build())
    }
}