package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.PagerListFragment
import com.github.chuross.qiiip.ui.fragment.presenter.TagItemListFragmentPresenter
import com.github.chuross.qiiip.ui.widget.adapter.ItemArrayAdapter
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

class TagItemListScreenFragment : PagerListFragment<TagItemListFragmentPresenter, Item>(), ScreenFragment {

    companion object {
        val ARGUMENT_KEY_TAG = "argument_key_tag"

        fun create(tag: Tag) = TagItemListScreenFragment().apply {
            arguments = Bundle().apply { putSerializable(ARGUMENT_KEY_TAG, tag) }
        }
    }

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.TAG_ITEM_LIST
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = TagItemListScreenFragment::class.java.name
    override val adapter: RecyclerViewCollectionAdapter<*, *> by lazy { ItemArrayAdapter(activity) }
    override val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(activity) }

    override fun createPresenter(): TagItemListFragmentPresenter = TagItemListFragmentPresenter(this)

}