package com.github.chuross.qiiip.ui.fragment.screen

import android.app.Fragment
import android.app.FragmentManager

class ScreenFragmentTransitionHelper(containerId: Int, manager: FragmentManager) {

    val containerId = containerId
    val manager = manager

    fun launchScreen(screenFragment: ScreenFragment) {
        screenFragment as Fragment

        if (manager.backStackEntryCount == 0) {
            manager.beginTransaction()
                    .replace(containerId, screenFragment, screenFragment.getScreenIdentity())
                    .commitAllowingStateLoss()
            return
        }

        val transaction = manager.beginTransaction()

        when (screenFragment.getScreenExitAction()) {
            ScreenExitAction.HIDE -> transaction.hide(screenFragment)
            ScreenExitAction.DETACH -> transaction.detach(screenFragment)
        }

        transaction.add(containerId, screenFragment, screenFragment.getScreenIdentity())
        transaction.addToBackStack(screenFragment.getScreen().getName())
        transaction.commitAllowingStateLoss()
    }

    fun popBackStack() {
        manager.popBackStack()
    }

    fun homeAsUp() {
        manager.findFragmentById(containerId)?.let { currentFragment ->
            currentFragment as ScreenFragment

            currentFragment.getScreen().getParent()?.let { parent ->
                manager.popBackStack(parent.getName(), 0)
            } ?: manager.popBackStack()
        }
    }
}