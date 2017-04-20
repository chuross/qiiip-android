package com.github.chuross.qiiip.ui.viewmodel.fragment

import android.content.Context
import com.github.chuross.qiiip.usecase.RxUseCase
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import jp.keita.kagurazaka.rxproperty.RxProperty
import jp.keita.kagurazaka.rxproperty.toRxProperty

typealias Result<T> = Pair<T?, Throwable?>

abstract class PagerListFragmentViewModel<T>(context: Context) : FragmentViewModel(context) {

    val success: RxProperty<List<T>> = RxProperty()
    val fail: RxProperty<Throwable> = RxProperty()
    val isInitialFetchFailed: RxProperty<Boolean> = fail.map { success.get()?.isEmpty() ?: true }.toRxProperty()
    val isLoading: RxProperty<Boolean> = RxProperty(false)
    val defaultPage: Int = 1
    val currentPage: RxProperty<Int> = RxProperty(defaultPage)
    val currentPageValue: Int get() = currentPage.get() ?: defaultPage
    val nextPage: Int get() = currentPage.get()!!.inc()
    private val composedUseCase: RxUseCase<List<T>> get() = useCase().compose {
        it.bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .doFinally { isLoading.set(false) }
                .subscribeOn(application.serialScheduler)
                .observeOn(application.mainThreadScheduler)
    }

    abstract fun useCase(): RxUseCase<List<T>>

    fun fetch() {
        isLoading.set(true)
        currentPage.set(defaultPage)
        success.set(listOf())

        composedUseCase.apply {
            disposables.add(this)
        }.exec({
            success.set(it)
        }, {
            fail.set(it)
        })
    }

    fun fetchNext() {
        isLoading.set(true)

        composedUseCase.apply {
            disposables.add(this)
        }.exec({
            val list = success.get() ?: listOf()
            success.set(list.plus(it))
        }, {
            fail.set(it)
        })
    }
}