package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.qiiip.ui.fragment.presenter.ItemDetailFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.template.ItemDetailFragmentTemplate

class ItemDetailFragment : BaseFragment<ItemDetailFragmentPresenter, ItemDetailFragmentTemplate>() {

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemDetailFragmentTemplate = ItemDetailFragmentTemplate(activity)

    override fun createPresenter(): ItemDetailFragmentPresenter = ItemDetailFragmentPresenter(this)
}