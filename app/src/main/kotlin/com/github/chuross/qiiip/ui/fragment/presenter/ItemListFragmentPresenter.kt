package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.template.ItemListTemplate

abstract class ItemListFragmentPresenter<FRAGMENT : Fragment>(fragment: FRAGMENT) : RequestFragmentPresenter<FRAGMENT, ItemListTemplate, List<Item>>(fragment) {

}