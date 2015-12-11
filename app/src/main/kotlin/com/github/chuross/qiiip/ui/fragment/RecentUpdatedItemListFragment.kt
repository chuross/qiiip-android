package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.common.ListFragment
import com.github.chuross.qiiip.ui.fragment.presenter.ItemListFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ItemListTemplate
import com.github.chuross.qiiip.ui.widget.adapter.ItemArrayAdapter
import rx.Observable

class RecentUpdatedItemListFragment : ListFragment<ItemListFragmentPresenter<RecentUpdatedItemListFragment>, ItemListTemplate, List<Item>>() {

    override val adapter: RecyclerView.Adapter<*> by lazy { ItemArrayAdapter(activity) }

    override val layoutManager: RecyclerView.LayoutManager by lazy { LinearLayoutManager(activity) }

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemListTemplate = ItemListTemplate(activity)

    override fun createPresenter(): ItemListFragmentPresenter<RecentUpdatedItemListFragment> = object : ItemListFragmentPresenter<RecentUpdatedItemListFragment>(this) {
        override fun request(): Observable<List<Item>> = application.getItemRepository().findAllByKeyword("android", 1, 20)
    }

    override fun onRequestSuccess(result: List<Item>) {
        (adapter as ItemArrayAdapter).addAll(result)
    }

}