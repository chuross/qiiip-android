package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.ui.fragment.screen.TagItemListScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.ListFragmentTemplate
import rx.Observable

class TagItemListFragmentPresenter(fragment: TagItemListScreenFragment) : PagerListFragmentPresenter<TagItemListScreenFragment, ListFragmentTemplate, Item>(fragment) {

    val tag: Tag by lazy { view.arguments.getSerializable(TagItemListScreenFragment.ARGUMENT_KEY_TAG) as Tag }

    override fun createTemplate(parent: ViewGroup, arguments: Bundle?): ListFragmentTemplate = ListFragmentTemplate(view.activity)

    override fun request(page: Int, initialize: Boolean): Observable<List<Item>> = view.application.itemRepository.findAllByTagIdentity(tag.identity, page, perPage)

}
