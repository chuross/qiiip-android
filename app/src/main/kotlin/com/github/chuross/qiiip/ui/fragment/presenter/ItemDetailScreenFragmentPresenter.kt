package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.view.ViewGroup
import com.github.chuross.library.mvp.presenter.SupportFragmentPresenter
import com.github.chuross.qiiip.ui.fragment.screen.ItemDetailScreenFragment
import com.github.chuross.qiiip.ui.fragment.template.ItemDetailScreenFragmentTemplate

class ItemDetailScreenFragmentPresenter(fragment: ItemDetailScreenFragment) : SupportFragmentPresenter<ItemDetailScreenFragment, ItemDetailScreenFragmentTemplate>(fragment) {

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ItemDetailScreenFragmentTemplate = ItemDetailScreenFragmentTemplate(view.activity)

}