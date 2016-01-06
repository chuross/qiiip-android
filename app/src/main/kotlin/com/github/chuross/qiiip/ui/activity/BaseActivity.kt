package com.github.chuross.qiiip.ui.activity

import android.os.Bundle
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.RxLifecycle
import com.trello.rxlifecycle.components.ActivityLifecycleProvider
import rx.Observable
import rx.Scheduler
import rx.android.schedulers.AndroidSchedulers
import rx.subjects.BehaviorSubject
import rx.subscriptions.CompositeSubscription

/**
 * @see
 * https://github.com/trello/RxLifecycle/blob/master/rxlifecycle-components/src/main/java/com/trello/rxlifecycle/components/support/RxAppCompatActivity.java
 */
abstract class BaseActivity<PRESENTER : ActivityPresenter<*, *>> : PresentationActivity<PRESENTER>(), ActivityLifecycleProvider {

    val subscriptions = CompositeSubscription()

    private val lifecycle = BehaviorSubject.create<ActivityEvent>()

    override fun lifecycle(): Observable<ActivityEvent>? = lifecycle.asObservable()

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent?): Observable.Transformer<T, T>? = RxLifecycle.bindUntilActivityEvent(lifecycle, event)

    override fun <T : Any?> bindToLifecycle(): Observable.Transformer<T, T>? = RxLifecycle.bindActivity(lifecycle)

    protected fun <T : Any?> complement(scheduler: Scheduler): Observable.Transformer<T, T> = object : Observable.Transformer<T, T> {
        override fun call(source: Observable<T>): Observable<T> {
            return source.compose(bindUntilEvent<T>(ActivityEvent.DESTROY))
                    .subscribeOn(scheduler)
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.onNext(ActivityEvent.CREATE);
    }

    override fun onStart() {
        super.onStart()
        lifecycle.onNext(ActivityEvent.START);
    }

    override fun onResume() {
        super.onResume()
        lifecycle.onNext(ActivityEvent.RESUME);
    }

    override fun onPause() {
        lifecycle.onNext(ActivityEvent.PAUSE);
        super.onPause()
    }

    override fun onStop() {
        lifecycle.onNext(ActivityEvent.STOP);
        super.onStop()
    }

    override fun onDestroy() {
        lifecycle.onNext(ActivityEvent.DESTROY);
        subscriptions.unsubscribe()
        super.onDestroy()
    }
}