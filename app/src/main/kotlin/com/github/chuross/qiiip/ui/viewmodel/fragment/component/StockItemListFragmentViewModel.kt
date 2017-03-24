package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.PagerListFragmentViewModel
import io.reactivex.Single

class StockItemListFragmentViewModel(context: Context) : PagerListFragmentViewModel<Item>(context) {

    override fun fetch() = application.authorizedUser?.let {
        if (!application.isAuthorized) fetch(Single.error(IllegalStateException("UnAuthorized")))
        return fetch(application.repositories.itemRepository.getStocksByUserIdentity(it.identity, defaultPage, Settings.app.perPage))
    } ?: fetch(Single.error(IllegalStateException("UnAuthorized")))

    override fun fetchNext() = application.authorizedUser?.let {
        if (!application.isAuthorized) fetch(Single.error(IllegalStateException("UnAuthorized")))
        return fetchNext(application.repositories.itemRepository.getStocksByUserIdentity(it.identity, nextPage, Settings.app.perPage))
    } ?: fetch(Single.error(IllegalStateException("UnAuthorized")))
}