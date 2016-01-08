package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.TagItemListFragment
import com.github.chuross.qiiip.ui.fragment.presenter.TagScreenFragmentPresenter

class TagScreenFragment : BaseFragment<TagScreenFragmentPresenter>(), ScreenFragment {

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

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        screenActivity.setUpToolbar(presenter.template.toolbar)
        presenter.template.toolbar.title = presenter.tag.identity.value

        presenter.template.apply(presenter.tag)
        childFragmentManager.beginTransaction().replace(R.id.list_container, TagItemListFragment.create(presenter.tag)).commit()
    }
}