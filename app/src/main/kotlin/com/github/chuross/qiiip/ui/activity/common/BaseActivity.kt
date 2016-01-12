package com.github.chuross.qiiip.ui.activity.common

import android.os.Bundle
import com.github.chuross.library.mvp.presenter.ActivityPresenter
import com.github.chuross.library.mvp.view.activity.PresentationActivity
import com.github.chuross.library.mvp.view.template.Template
import com.trello.rxlifecycle.ActivityEvent
import com.trello.rxlifecycle.RxLifecycle
import com.trello.rxlifecycle.components.ActivityLifecycleProvider
import rx.Observable
import rx.subjects.BehaviorSubject

/**
 * @see
 * https://github.com/trello/RxLifecycle/blob/master/rxlifecycle-components/src/main/java/com/trello/rxlifecycle/components/support/RxAppCompatActivity.java
 */
abstract class BaseActivity<P : ActivityPresenter<*>, T : Template> : PresentationActivity<P, T>(), ActivityLifecycleProvider {

    private val lifecycle = BehaviorSubject.create<ActivityEvent>()

    override fun lifecycle(): Observable<ActivityEvent>? = lifecycle.asObservable()

    override fun <T : Any?> bindUntilEvent(event: ActivityEvent?): Observable.Transformer<T, T>? = RxLifecycle.bindUntilActivityEvent(lifecycle, event)

    override fun <T : Any?> bindToLifecycle(): Observable.Transformer<T, T>? = RxLifecycle.bindActivity(lifecycle)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycle.onNext(ActivityEvent.CREATE)
    }

    override fun onStart() {
        super.onStart()
        lifecycle.onNext(ActivityEvent.START)
    }

    override fun onResume() {
        super.onResume()
        lifecycle.onNext(ActivityEvent.RESUME)
    }

    override fun onPause() {
        lifecycle.onNext(ActivityEvent.PAUSE)
        super.onPause()
    }

    override fun onStop() {
        lifecycle.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    override fun onDestroy() {
        lifecycle.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }
}