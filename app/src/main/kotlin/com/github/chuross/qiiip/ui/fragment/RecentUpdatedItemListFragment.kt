package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.presenter.ItemListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.screen.ItemDetailScreenFragment
import com.github.chuross.qiiip.ui.widget.adapter.ItemArrayAdapter
import com.github.chuross.qiiip.ui.widget.adapter.RecyclerViewCollectionAdapter
import rx.Observable

class RecentUpdatedItemListFragment : PagerListFragment<ItemListFragmentPresenter<RecentUpdatedItemListFragment>, Item>() {

    override val adapter: RecyclerViewCollectionAdapter<*, *> by lazy { ItemArrayAdapter(activity) }

    override val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(activity) }

    override fun createPresenter(): ItemListFragmentPresenter<RecentUpdatedItemListFragment> = object : ItemListFragmentPresenter<RecentUpdatedItemListFragment>(this) {
        override fun request(page: Int, initialize: Boolean): Observable<List<Item>> = application.itemRepository.findAll(page, resources.getInteger(R.integer.per_page))
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        (adapter as ItemArrayAdapter).clickListener = { view, item ->
            screenActivity.launchScreen(ItemDetailScreenFragment.create(item))
        }
    }

}
