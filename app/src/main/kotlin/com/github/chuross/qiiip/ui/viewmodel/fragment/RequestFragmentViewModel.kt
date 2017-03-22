package com.github.chuross.qiiip.ui.viewmodel.fragment

import android.content.Context
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.kotlin.bindToLifecycle
import com.trello.rxlifecycle2.kotlin.bindUntilEvent
import io.reactivex.Single
import jp.keita.kagurazaka.rxproperty.RxProperty
import timber.log.Timber

abstract class RequestFragmentViewModel<T>(context: Context) : FragmentViewModel(context) {

    val fetchedResult: RxProperty<T> = RxProperty()
    val isLoading: RxProperty<Boolean> = RxProperty(false)
    val hasError: RxProperty<Boolean> = RxProperty(false)

    override fun create() {
        super.create()
        isLoading.filter { it }
                .bindToLifecycle(this)
                .subscribe({ hasError.set(false) })
                .apply { disposables.add(this) }
    }

    fun fetch(source: Single<T>, onSuccess: ((T) -> Unit)?, onError: ((Throwable) -> Unit)?) {
        isLoading.set(true)
        source.bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribeOn(application.serialScheduler)
                .observeOn(application.mainThreadScheduler)
                .subscribe({
                    onSuccess?.invoke(it)
                    hasError.set(false)
                    isLoading.set(false)
                }, {
                    Timber.e(it)
                    onError?.invoke(it)
                    hasError.set(true)
                    isLoading.set(false)
                })
                .apply { disposables.add(this) }
    }
}