package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.screen.TagScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.TagScreenFragmentTemplate

class TagScreenFragmentPresenter(fragment: TagScreenFragment) : SupportFragmentPresenter<TagScreenFragment, TagScreenFragmentTemplate>(fragment) {

    val tag: Tag by lazy { view.arguments.getSerializable(TagScreenFragment.ARGUMENT_KEY_TAG) as Tag }

    override fun createTemplate(parent: ViewGroup, arguments: Bundle?): TagScreenFragmentTemplate = TagScreenFragmentTemplate(view.activity)

}
