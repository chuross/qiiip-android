package com.github.chuross.qiiip.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class FragmentPagerAdapter(fragmentManager: FragmentManager, private val items: List<Pair<String, (() -> Fragment)>>) : FragmentPagerAdapter(fragmentManager) {

    override fun getCount(): Int = items.size

    override fun getPageTitle(position: Int): CharSequence = items[position].first

    override fun getItem(position: Int): Fragment = items[position].second.invoke()
}