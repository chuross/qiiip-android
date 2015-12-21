package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.presenter.ItemDetailScreenFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ItemDetailScreenFragmentTemplate

class ItemDetailScreenFragment : BaseFragment<ItemDetailScreenFragmentPresenter, ItemDetailScreenFragmentTemplate>(), ScreenFragment {

    companion object {
        private val ARGUMENT_KEY_ITEM = "argument_key_item"

        fun create(item: Item): ItemDetailScreenFragment {
            val arguments = Bundle()
            arguments.putSerializable(ARGUMENT_KEY_ITEM, item)

            val fragment = ItemDetailScreenFragment()
            fragment.arguments = arguments
            return fragment
        }
    }

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.ITEM_DETAIL
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = ItemDetailScreenFragment::class.java.name

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemDetailScreenFragmentTemplate = ItemDetailScreenFragmentTemplate(activity)

    override fun createPresenter(): ItemDetailScreenFragmentPresenter = ItemDetailScreenFragmentPresenter(this)

    override fun onViewCreated(template: ItemDetailScreenFragmentTemplate, savedInstanceState: Bundle?) {
        super.onViewCreated(template, savedInstanceState)

        screenActivity.setUpToolbar(template.toolbar)

        val item = arguments.getSerializable(ARGUMENT_KEY_ITEM) as Item
        template.apply(item)
    }
}