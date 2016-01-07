package com.github.chuross.qiiip.ui.fragment.screen

import android.os.Bundle
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.infrastructure.rx.RxExtraView
import com.github.chuross.qiiip.ui.fragment.BaseFragment
import com.github.chuross.qiiip.ui.fragment.presenter.ItemDetailScreenFragmentPresenter
import com.jakewharton.rxbinding.view.RxView
import rx.android.schedulers.AndroidSchedulers

class ItemDetailScreenFragment : BaseFragment<ItemDetailScreenFragmentPresenter>(), ScreenFragment {

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

    override fun onViewCreated(savedInstanceState: Bundle?) {
        super.onViewCreated(savedInstanceState)

        screenActivity.setUpToolbar(presenter.template.toolbar)
        presenter.template.toolbar.inflateMenu(R.menu.menu_item_detail)
        presenter.template.apply(presenter.item)

        subscriptions.add(RxExtraView.tagClicks(presenter.template.tagGroup)
                .map { presenter.getTag(TagIdentity(it)) as Tag }
                .compose(complement<Tag>(AndroidSchedulers.mainThread()))
                .subscribe({ tag ->
                    screenActivity.launchScreen(TagItemListScreenFragment.create(tag))
                }))

        presenter.item.metaInfo?.let { metaInfo ->
            subscriptions.add(RxView.clicks(presenter.template.userLayout)
                    .compose(complement<Void>(AndroidSchedulers.mainThread()))
                    .subscribe {
                        screenActivity.launchScreen(UserScreenFragment.create(metaInfo.user))
                    })
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.template.webView.onResume()
    }

    override fun onPause() {
        presenter.template.webView.onPause()
        super.onPause()
    }

    override fun onDestroyView() {
        presenter.template.webView.destroy()
        super.onDestroyView()
    }
}