package com.github.chuross.qiiip.ui.fragment.presenter

import android.support.v4.app.Fragment
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.template.ListFragmentTemplate

abstract class ItemListFragmentPresenter<F : Fragment>(fragment: F) : PagerListFragmentPresenter<F, ListFragmentTemplate, List<Item>>(fragment) {

}