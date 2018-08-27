package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import com.github.chuross.morirouter.MoriBinder
import com.github.chuross.morirouter.annotation.Argument
import com.github.chuross.morirouter.annotation.RouterPath
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.databinding.FragmentItemDetailScreenBinding
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.ItemDetailScreenFragmentViewModel
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.ItemDetailScreenFragmentViewModelBuilder
import com.zzhoujay.richtext.RichText

@RouterPath(name = "item_detail")
class ItemDetailScreenFragment : BaseFragment<FragmentItemDetailScreenBinding>() {

    @Argument
    lateinit var item: Item
    override val layoutResourceId: Int = R.layout.fragment_item_detail_screen
    private lateinit var viewModel: ItemDetailScreenFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        MoriBinder.bind(this)
        super.onCreate(savedInstanceState)

        viewModel = ItemDetailScreenFragmentViewModelBuilder(requireContext(), item).build(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewModel = viewModel
        binding.executePendingBindings()

        binding.toolbar.setNavigationOnClickListener { screenActivity.router.pop() }
        binding.tagGroup.apply {
            setTags(item.tags.map { it.identity.value })
            setOnTagClickListener { tagName ->
                screenActivity.router.tag(item.tags.first { it.identity.value == tagName }).launch()
            }
        }
        binding.userLayout.setOnClickListener {
            screenActivity.router.userDetail(viewModel.item.user).addSharedElement(binding.profileImg).launch()
        }
        binding.stockBtn.setOnClickListener {
            viewModel.toggleStock()
        }

        viewModel.item.body?.let { RichText.fromMarkdown(it).into(binding.bodyTxt) }
    }
}