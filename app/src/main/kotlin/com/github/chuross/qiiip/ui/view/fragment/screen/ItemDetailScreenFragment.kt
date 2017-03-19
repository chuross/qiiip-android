package com.github.chuross.qiiip.ui.view.fragment.screen

import android.os.Bundle
import android.view.View
import com.github.chuross.qiiip.R
import com.github.chuross.qiiip.application.event.ScreenPopEvent
import com.github.chuross.qiiip.databinding.FragmentItemDetailScreenBinding
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.view.fragment.BaseFragment
import com.github.chuross.qiiip.ui.viewmodel.fragment.ItemDetailScreenFragmentViewModel
import com.hannesdorfmann.fragmentargs.FragmentArgs
import com.hannesdorfmann.fragmentargs.annotation.Arg
import com.hannesdorfmann.fragmentargs.annotation.FragmentWithArgs
import com.michaelflisar.rxbus2.RxBus
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
        binding?.toolbar?.apply {
            setNavigationIcon(R.drawable.ic_arrow_back_white_24dp)
            setNavigationOnClickListener { RxBus.get().send(ScreenPopEvent()) }
        }
        binding?.tagGroup?.setTags(item.tags.map { it.identity.value })
        viewModel.item.body?.let { RichText.fromMarkdown(it).into(binding?.bodyTxt) }
    }
}