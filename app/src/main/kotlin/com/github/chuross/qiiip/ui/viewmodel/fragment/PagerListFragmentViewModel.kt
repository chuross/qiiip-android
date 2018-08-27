package com.github.chuross.qiiip.ui.viewmodel.fragment

import com.github.chuross.qiiip.ui.viewmodel.AndroidViewModel
import com.github.chuross.qiiip.usecase.RxUseCase
import io.reactivex.subjects.BehaviorSubject
import jp.keita.kagurazaka.rxproperty.RxProperty

abstract class PagerListFragmentViewModel<T> : AndroidViewModel() {

    val list: RxProperty<List<T>> = RxProperty()
    val fail: BehaviorSubject<Throwable> = BehaviorSubject.create()
    val isLoading: RxProperty<Boolean> = RxProperty(false)
    val defaultPage: Int = 1
    val currentPage: RxProperty<Int> = RxProperty(defaultPage)
    val currentPageValue: Int get() = currentPage.get() ?: defaultPage
    val nextPage: Int get() = currentPage.get()!!.inc()
    private val composedUseCase: RxUseCase<List<T>>
        get() = useCase().compose {
            it.subscribeOn(qiiipApplication.threadPoolScheduler)
                    .observeOn(qiiipApplication.mainThreadScheduler)
                    .doFinally { isLoading.set(false) }
        }

    abstract fun useCase(): RxUseCase<List<T>>

    fun fetch() {
        isLoading.set(true)
        currentPage.set(defaultPage)
        list.set(listOf())

        composedUseCase.apply {
            disposables.add(this)
        }.exec({
            list.set(it)
            currentPage.set(currentPageValue.inc())
        }, {
            fail.onNext(it)
        })
    }

    fun fetchNext() {
        isLoading.set(true)

        composedUseCase.apply {
            disposables.add(this)
        }.exec({
            val list = list.get() ?: listOf()
            this.list.set(list.plus(it))
            currentPage.set(currentPageValue.inc())
        }, {
            fail.onNext(it)
        })
    }
}