package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.domain.tag.Tag
import com.github.chuross.qiiip.domain.tag.TagIdentity
import com.github.chuross.qiiip.ui.fragment.screen.ItemDetailScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.ItemDetailScreenFragmentTemplate

class ItemDetailScreenFragmentPresenter(fragment: ItemDetailScreenFragment) : SupportFragmentPresenter<ItemDetailScreenFragment, ItemDetailScreenFragmentTemplate>(fragment) {

    val item: Item by lazy { view.arguments.getSerializable(ItemDetailScreenFragment.ARGUMENT_KEY_ITEM) as Item }

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemDetailScreenFragmentTemplate = ItemDetailScreenFragmentTemplate(view.activity)

    fun getTag(tagIdentity: TagIdentity): Tag? = item.metaInfo?.let { it.tags.single { tag -> tag.identity.value.equals(tagIdentity.value) } }

}