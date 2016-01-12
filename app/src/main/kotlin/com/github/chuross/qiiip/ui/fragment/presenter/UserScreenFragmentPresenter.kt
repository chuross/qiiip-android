package com.github.chuross.qiiip.ui.fragment.presenter

import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.screen.UserScreenFragment
import rx.Observable

class UserScreenFragmentPresenter(fragment: UserScreenFragment) : PagerListFragmentPresenter<UserScreenFragment, Item>(fragment) {

    override fun request(page: Int, initialize: Boolean): Observable<List<Item>> = view.application.itemRepository.findAllByUserIdentity(view.userIdentity, page, perPage)

}