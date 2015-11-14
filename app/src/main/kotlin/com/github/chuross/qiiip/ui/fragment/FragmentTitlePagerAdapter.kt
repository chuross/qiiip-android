package com.github.chuross.qiiip.ui.fragment

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import android.support.v4.app.FragmentPagerAdapter.POSITION_NONE

class FragmentTitlePagerAdapter(manager: FragmentManager, items: List<Pair<String, () -> Fragment>>) : FragmentPagerAdapter(manager) {

    val items: List<Pair<String, () -> Fragment>> = items

    override fun getCount(): Int = items.count()

    override fun getItem(position: Int): Fragment? = items[position].second.invoke()

    override fun getPageTitle(position: Int): CharSequence? = items[position].first

    override fun getItemPosition(`object`: Any?): Int = POSITION_NONE
}