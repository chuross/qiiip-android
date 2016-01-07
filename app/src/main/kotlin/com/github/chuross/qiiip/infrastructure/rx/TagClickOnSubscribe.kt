package com.github.chuross.qiiip.infrastructure.rx

import com.jakewharton.rxbinding.internal.MainThreadSubscription
import com.jakewharton.rxbinding.internal.Preconditions
import me.gujun.android.taggroup.TagGroup
import rx.Observable
import rx.Subscriber

class TagClickOnSubscribe(val tagLayout: TagGroup) : Observable.OnSubscribe<String> {

    override fun call(subscriber: Subscriber<in String>) {
        Preconditions.checkUiThread()

        tagLayout.setOnTagClickListener { if (!subscriber.isUnsubscribed) subscriber.onNext(it) }

        subscriber.add(object : MainThreadSubscription() {
            override fun onUnsubscribe() {
                tagLayout.setOnTagClickListener(null)
            }
        })
    }

}