package com.github.chuross.qiiip.infrastructure.rx

import me.gujun.android.taggroup.TagGroup
import rx.Observable

class RxExtraView {

    private constructor()

    companion object {

        fun tagClicks(tagGroup: TagGroup): Observable<String> = Observable.create(TagClickOnSubscribe(tagGroup))
    }
}