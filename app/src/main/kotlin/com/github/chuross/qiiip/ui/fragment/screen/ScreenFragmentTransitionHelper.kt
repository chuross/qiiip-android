package com.github.chuross.qiiip.ui.fragment.screen

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager


class ScreenFragmentTransitionHelper(containerId: Int, manager: FragmentManager) {

    private val containerId = containerId
    private val manager = manager

    fun launchScreen(screenFragment: ScreenFragment) {
        screenFragment as Fragment

        if (manager.backStackEntryCount == 0) {
            manager.beginTransaction()
                    .replace(containerId, screenFragment, screenFragment.screenIdentity)
                    .commitAllowingStateLoss()
            return
        }

        val currentScreenFragment = manager.findFragmentById(containerId) as ScreenFragment;
        if (currentScreenFragment.screenIdentity.equals(screenFragment.screenIdentity)) {
            return;
        }

        val transaction = manager.beginTransaction()

        when (screenFragment.screenExitAction) {
            ScreenExitAction.HIDE -> transaction.hide(screenFragment)
            ScreenExitAction.DETACH -> transaction.detach(screenFragment)
        }

        transaction.add(containerId, screenFragment, screenFragment.screenIdentity)
        transaction.addToBackStack(screenFragment.screen.toString())
        transaction.commitAllowingStateLoss()
    }

    fun popBackStack() {
        manager.popBackStack()
    }

    fun homeAsUp() {
        manager.findFragmentById(containerId)?.let { currentFragment ->
            currentFragment as ScreenFragment

            currentFragment.screen.parent?.let { parent ->
                manager.popBackStack(parent.toString(), 0)
            } ?: manager.popBackStack()
        }
    }
}