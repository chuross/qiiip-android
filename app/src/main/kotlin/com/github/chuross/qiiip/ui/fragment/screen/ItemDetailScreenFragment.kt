package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.presenter.ItemDetailScreenFragmentPresenter

class ItemDetailScreenFragment : BaseFragment<ItemDetailScreenFragmentPresenter>(), ScreenFragment {

    companion object {
        private val ARGUMENT_KEY_ITEM = "argument_key_item"

        fun create(item: Item): ItemDetailScreenFragment {
            return ItemDetailScreenFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARGUMENT_KEY_ITEM, item)
                }
            }
        }
    }

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.ITEM_DETAIL
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = ItemDetailScreenFragment::class.java.name


    override fun createPresenter(): ItemDetailScreenFragmentPresenter = ItemDetailScreenFragmentPresenter(this)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        screenActivity.setUpToolbar(presenter.template.toolbar)

        val item = arguments.getSerializable(ARGUMENT_KEY_ITEM) as Item
        presenter.template.apply(item)
    }
}