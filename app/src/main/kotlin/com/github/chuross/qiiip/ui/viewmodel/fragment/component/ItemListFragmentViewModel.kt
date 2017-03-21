package com.github.chuross.qiiip.ui.viewmodel.fragment.component

import android.content.Context
import com.github.chuross.qiiip.Settings
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.viewmodel.fragment.FragmentViewModel
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.functions.Consumer
import jp.keita.kagurazaka.rxproperty.RxProperty
import timber.log.Timber

class ItemListFragmentViewModel(context: Context) : FragmentViewModel(context) {

    val items: RxProperty<List<Item>> = RxProperty()
    var currentPage: RxProperty<Int> = RxProperty(Settings.app.defaultPage)
    var isLoading: RxProperty<Boolean> = RxProperty(false)
    var hasError: RxProperty<Boolean> = RxProperty(false)

    init {
        isLoading.filter { it }
                .subscribe({ hasError.set(false) })
                .apply { disposables.add(this) }
    }

    fun fetchItems() = fetchItems(Settings.app.defaultPage, Consumer {
        items.set(it)
    })

    fun fetchNextItems() = fetchItems(currentPage.get()!!.inc(), Consumer { result ->
        items.get()?.let { items.set(it.plus(result)) }
        currentPage.get()?.let { currentPage.set(it.inc()) }
    })

    private fun fetchItems(page: Int, success: Consumer<List<Item>>) {
        isLoading.set(true)
        application.itemRepository.findAll(page, Settings.app.perPage)
                .bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribeOn(application.serialScheduler)
                .observeOn(application.mainThreadScheduler)
                .subscribe({
                    success.accept(it)
                    hasError.set(false)
                    isLoading.set(false)
                }, {
                    Timber.e(it)
                    hasError.set(true)
                    isLoading.set(false)
                })
                .apply { disposables.add(this) }
    }
}