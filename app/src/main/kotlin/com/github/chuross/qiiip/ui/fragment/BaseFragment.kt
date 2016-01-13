package com.github.chuross.qiiip.ui.fragment

import android.app.Activity
import android.os.Bundle
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.library.mvp.view.fragment.SupportPresentationFragment
import com.github.chuross.library.mvp.view.template.Template
import com.github.chuross.qiiip.application.Application
import com.github.chuross.qiiip.ui.activity.ScreenActivity
import com.trello.rxlifecycle.FragmentEvent
import com.trello.rxlifecycle.RxLifecycle
import com.trello.rxlifecycle.components.FragmentLifecycleProvider
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject
import rx.subscriptions.CompositeSubscription

/**
 * @see
 * https://github.com/trello/RxLifecycle/blob/master/rxlifecycle-components/src/main/java/com/trello/rxlifecycle/components/support/RxFragment.java
 */
abstract class BaseFragment<P : SupportFragmentPresenter<*>, T : Template> : SupportPresentationFragment<P, T>(), FragmentLifecycleProvider {

    val screenActivity by lazy { activity as ScreenActivity }
    val application by lazy { Application.from(activity) }
    val subscriptions = CompositeSubscription()

    private val lifecycle = BehaviorSubject.create<FragmentEvent>()

    override fun lifecycle(): Observable<FragmentEvent>? = lifecycle.asObservable()

    override fun <T : Any?> bindUntilEvent(event: FragmentEvent?): Observable.Transformer<T, T>? = RxLifecycle.bindUntilFragmentEvent(lifecycle, event)

    override fun <T : Any?> bindToLifecycle(): Observable.Transformer<T, T>? = RxLifecycle.bindFragment(lifecycle)

    protected fun <T : Any?> complement(scheduler: Scheduler): Observable.Transformer<T, T> = object : Observable.Transformer<T, T> {
        override fun call(source: Observable<T>): Observable<T> {
            return source.compose(bindUntilEvent<T>(FragmentEvent.DESTROY_VIEW))
                    .subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    @Suppress("deprecation")
    override fun onAttach(activity: Activity?) {
        super.onAttach(activity)
        lifecycle.onNext(FragmentEvent.ATTACH)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.onNext(FragmentEvent.CREATE)
    }

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)
        lifecycle.onNext(FragmentEvent.CREATE_VIEW)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.onNext(FragmentEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycle.onNext(FragmentEvent.RESUME)
    }

    override fun onPause() {
        lifecycle.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycle.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    override fun onDestroyView() {
        lifecycle.onNext(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    override fun onDestroy() {
        lifecycle.onNext(FragmentEvent.DESTROY)
        subscriptions.unsubscribe()
        super.onDestroy()
    }

    override fun onDetach() {
        lifecycle.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }
}