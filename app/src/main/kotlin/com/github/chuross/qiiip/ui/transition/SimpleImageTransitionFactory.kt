package com.github.chuross.qiiip.ui.transition

import androidx.transition.ChangeBounds
import androidx.transition.ChangeImageTransform
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import com.github.chuross.morirouter.core.TransitionFactory

class SimpleImageTransitionFactory : TransitionFactory {

    override fun create(): Any {
        return TransitionSet().also {
            it.ordering = TransitionSet.ORDERING_TOGETHER
            it.addTransition(ChangeBounds())
            it.addTransition(ChangeTransform())
            it.addTransition(ChangeImageTransform())
        }
    }
}