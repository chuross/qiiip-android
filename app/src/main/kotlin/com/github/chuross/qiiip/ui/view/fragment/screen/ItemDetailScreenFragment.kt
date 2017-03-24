package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.screen.TagScreen
import com.github.chuross.qiiip.databinding.FragmentItemDetailScreenBinding
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.extension.bindPaddingView
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.screen.ItemDetailScreenFragmentViewModel
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.zzhoujay.richtext.RichText

@FragmentWithArgs
class ItemDetailScreenFragment : BaseFragment<FragmentItemDetailScreenBinding>() {

    @Arg
    lateinit var item: Item
    override val layoutResourceId: Int = R.layout.fragment_item_detail_screen
    private lateinit var viewModel: ItemDetailScreenFragmentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        FragmentArgs.inject(this)
        viewModel = ItemDetailScreenFragmentViewModel(context, item)
        bindViewModel(viewModel)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.viewModel = viewModel
        binding?.toolbar?.setNavigationOnClickListener { application.popScreen() }
        binding?.tagGroup?.apply {
            setTags(item.tags.map { it.identity.value })
            setOnTagClickListener { tagName ->
                application.startScreen(TagScreen(item.tags.first { it.identity.value == tagName }))
            }
        }
        binding?.appbar?.bindPaddingView(binding?.headerPadding)

        viewModel.item.body?.let { RichText.fromMarkdown(it).into(binding?.bodyTxt) }
    }
}