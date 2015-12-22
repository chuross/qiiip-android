package com.github.chuross.qiiip.ui.fragment.presenter

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.ViewGroup
import com.github.chuross.qiiip.domain.item.Item
import com.github.chuross.qiiip.ui.fragment.template.ListFragmentTemplate

abstract class ItemListFragmentPresenter<F : Fragment>(fragment: F) : PagerListFragmentPresenter<F, ListFragmentTemplate, List<Item>>(fragment) {

    override fun createTemplate(p0: ViewGroup?, p1: Bundle?): ListFragmentTemplate = ListFragmentTemplate(view.activity)

}