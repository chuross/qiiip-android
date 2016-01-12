package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.TagItemListFragment
import com.github.chuross.qiiip.ui.fragment.presenter.TagScreenFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.TagScreenFragmentTemplate

class TagScreenFragment : BaseFragment<TagScreenFragmentPresenter, TagScreenFragmentTemplate>(), ScreenFragment {

    companion object {
        val ARGUMENT_KEY_TAG = "argument_key_tag"

        fun create(tag: Tag) = TagScreenFragment().apply {
            arguments = Bundle().apply { putSerializable(ARGUMENT_KEY_TAG, tag) }
        }
    }

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.TAG_ITEM_LIST
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = TagScreenFragment::class.java.name

    override fun createPresenter(): TagScreenFragmentPresenter = TagScreenFragmentPresenter(this)

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): TagScreenFragmentTemplate = TagScreenFragmentTemplate(activity)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        screenActivity.setUpToolbar(template.toolbar)
        template.toolbar.title = presenter.tag.identity.value

        template.apply(presenter.tag)
        childFragmentManager.beginTransaction().replace(R.id.list_container, TagItemListFragment.create(presenter.tag)).commit()
    }
}