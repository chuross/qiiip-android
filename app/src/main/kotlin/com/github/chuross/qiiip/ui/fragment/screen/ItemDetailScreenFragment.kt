package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.infrastructure.rx.RxExtraView
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.presenter.ItemDetailScreenFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ItemDetailScreenFragmentTemplate
import com.jakewharton.rxbinding.view.RxView
import rx.android.schedulers.AndroidSchedulers

class ItemDetailScreenFragment : BaseFragment<ItemDetailScreenFragmentPresenter, ItemDetailScreenFragmentTemplate>(), ScreenFragment {

    companion object {
        val ARGUMENT_KEY_ITEM = "argument_key_item"

        fun create(item: Item): ItemDetailScreenFragment = ItemDetailScreenFragment().apply {
            arguments = Bundle().apply { putSerializable(ARGUMENT_KEY_ITEM, item) }
        }
    }

    override val screen: Screen = com.github.chuross.qiiip.ui.Screen.ITEM_DETAIL
    override val screenExitAction: ScreenExitAction = ScreenExitAction.HIDE
    override val screenIdentity: String = ItemDetailScreenFragment::class.java.name


    override fun createPresenter(): ItemDetailScreenFragmentPresenter = ItemDetailScreenFragmentPresenter(this)

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemDetailScreenFragmentTemplate = ItemDetailScreenFragmentTemplate(activity)

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        screenActivity.setUpToolbar(template.toolbar)
        template.toolbar.inflateMenu(R.menu.menu_item_detail)
        template.apply(presenter.item)

        subscriptions.add(RxExtraView.tagClicks(template.tagGroup)
                .map { presenter.getTag(TagIdentity(it)) as Tag }
                .compose(complement<Tag>(AndroidSchedulers.mainThread()))
                .subscribe { tag ->
                    screenActivity.launchScreen(TagScreenFragment.create(tag))
                })

        presenter.item.metaInfo?.let { metaInfo ->
            subscriptions.add(RxView.clicks(template.userLayout)
                    .compose(complement<Void>(AndroidSchedulers.mainThread()))
                    .subscribe {
                        screenActivity.launchScreen(UserScreenFragment.create(metaInfo.user))
                    })
        }
    }

    override fun onResume() {
        super.onResume()
        template.webView.onResume()
    }

    override fun onPause() {
        template.webView.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        template.webView.destroy()
        super.onDestroyView()
    }
}