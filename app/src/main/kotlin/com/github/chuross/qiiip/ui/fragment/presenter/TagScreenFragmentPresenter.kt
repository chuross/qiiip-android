package com.github.chuross.qiiip.ui.fragment.presenter

import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.screen.TagScreenFragment

class TagScreenFragmentPresenter(fragment: TagScreenFragment) : SupportFragmentPresenter<TagScreenFragment>(fragment) {

    val tag: Tag by lazy { view.arguments.getSerializable(TagScreenFragment.ARGUMENT_KEY_TAG) as Tag }

}
