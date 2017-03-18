package com.github.chuross.qiiip.ui.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FragmentPagerAdapter(fragmentManager: FragmentManager, private val items: List<Pair<String, (() -> Fragment)>>) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence = items[position].first

    override fun getItem(position: Int): Fragment = items[position].second.invoke()
}