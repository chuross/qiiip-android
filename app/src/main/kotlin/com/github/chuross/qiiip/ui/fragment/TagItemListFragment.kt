package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.presenter.TagItemListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.screen.ItemDetailScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.ListFragmentTemplate
import com.github.chuross.qiiip.ui.widget.adapter.ItemArrayAdapter
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter

class TagItemListFragment : PagerListFragment<TagItemListFragmentPresenter, ListFragmentTemplate, Item>() {

    companion object {
        val ARGUMENT_KEY_TAG = "argument_key_tag"

        fun create(tag: Tag) = TagItemListFragment().apply {
            arguments = Bundle().apply { putSerializable(ARGUMENT_KEY_TAG, tag) }
        }
    }

    override val adapter: RecyclerViewCollectionAdapter<*, *> by lazy { ItemArrayAdapter(activity) }
    override val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(activity) }

    override fun createPresenter(): TagItemListFragmentPresenter = TagItemListFragmentPresenter(this)

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ListFragmentTemplate = ListFragmentTemplate(activity)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        (adapter as ItemArrayAdapter).clickListener = { view, item ->
            screenActivity.launchScreen(ItemDetailScreenFragment.create(item))
        }
    }
}