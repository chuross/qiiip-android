package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.chuross.qiiip.R
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.screen.UserScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.UserScreenFragmentTemplate
import rx.Observable

class UserScreenFragmentPresenter(fragment: UserScreenFragment) : PagerListFragmentPresenter<UserScreenFragment, UserScreenFragmentTemplate, List<Item>>(fragment) {

    override fun createTemplate(parent: ViewGroup, p1: Bundle?): UserScreenFragmentTemplate = UserScreenFragmentTemplate(view.activity, parent)

    override fun request(page: Int, initialize: Boolean): Observable<List<Item>> = view.application.itemRepository.findAllByUserIdentity(view.userIdentity, page, view.resources.getInteger(R.integer.per_page))

}