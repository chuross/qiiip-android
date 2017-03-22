package com.github.chuross.qiiip.ui.viewmodel.fragment

import android.content.Context
import io.reactivex.Single
import jp.keita.kagurazaka.rxproperty.RxProperty

abstract class PagerListFragmentViewModel<T>(context: Context) : RequestFragmentViewModel<List<T>>(context) {

    val currentPage: RxProperty<Int> = RxProperty(defaultPage)
    val defaultPage: Int get() = 1
    val nextPage: Int get() = currentPage.get()!!.inc()

    protected fun fetch(source: Single<List<T>>) = fetch(source, {
        currentPage.set(defaultPage)
        fetchedResult.set(it)
    }, null)

    protected fun fetchNext(source: Single<List<T>>) = fetch(source, {
        currentPage.set(currentPage.get()!!.inc())
        fetchedResult.set(fetchedResult.get()!!.plus(it))
    }, null)
}