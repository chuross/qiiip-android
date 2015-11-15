package com.github.chuross.qiiip.ui.fragment

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class EntryListFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        container?.setBackgroundResource(android.R.color.holo_blue_dark)
        return super.onCreateView(inflater, container, savedInstanceState)
    }
}