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
    val error: RxProperty<Pair<Boolean, Throwable?>> = RxProperty()

    override fun create() {
        super.create()
        isLoading.filter { it }
                .bindToLifecycle(this)
                .subscribe({ error.set(Pair(false, null)) })
                .apply { disposables.add(this) }
    }

    fun fetch(source: Single<T>, onSuccess: ((T) -> Unit)?, onError: ((Throwable) -> Unit)?) {
        isLoading.set(true)
        source.bindUntilEvent(this, FragmentEvent.DESTROY_VIEW)
                .subscribeOn(application.serialScheduler)
                .observeOn(application.mainThreadScheduler)
                .subscribe({
                    onSuccess?.invoke(it)
                    error.set(Pair(false, null))
                    isLoading.set(false)
                }, {
                    Timber.e(it)
                    onError?.invoke(it)
                    error.set(Pair(false, it))
                    isLoading.set(false)
                })
                .apply { disposables.add(this) }
    }
}