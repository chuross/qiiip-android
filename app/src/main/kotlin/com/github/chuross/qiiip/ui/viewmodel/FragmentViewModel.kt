package com.github.chuross.qiiip.ui.viewmodel

import android.content.Context
import com.trello.rxlifecycle2.LifecycleProvider
import com.trello.rxlifecycle2.LifecycleTransformer
import com.trello.rxlifecycle2.RxLifecycle
import com.trello.rxlifecycle2.android.FragmentEvent
import com.trello.rxlifecycle2.android.RxLifecycleAndroid
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

abstract class FragmentViewModel(override val context: Context) : ViewModel, LifecycleProvider<FragmentEvent> {

    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()

    override fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.hide()
    }

    override fun <T : Any?> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycleAndroid.bindFragment(lifecycleSubject)
    }

    override fun <T : Any?> bindUntilEvent(event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    fun notifyLifecycleEvent(event: FragmentEvent) {
        lifecycleSubject.onNext(event)
    }
}