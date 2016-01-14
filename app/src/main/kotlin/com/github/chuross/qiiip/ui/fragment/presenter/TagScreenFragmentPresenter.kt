package com.github.chuross.qiiip.ui.fragment.presenter

import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.screen.TagScreenFragment
import rx.Observable

class TagScreenFragmentPresenter(fragment: TagScreenFragment) : SupportFragmentPresenter<TagScreenFragment>(fragment) {

    val partialTag: Tag by lazy { view.arguments.getSerializable(TagScreenFragment.ARGUMENT_KEY_TAG) as Tag }
    var tag: Tag? = null

    fun fetchTag(): Observable<Tag> = view.application.tagRepository.find(partialTag.identity)
            .doOnNext { tag = it }

}
