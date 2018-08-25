package com.github.chuross.qiiip.ui.transition

import androidx.transition.*
import com.github.chuross.morirouter.core.TransitionFactory

class SimpleImageTransitionFactory : TransitionFactory {

    override fun create(): Any {
        return TransitionSet().also {
            it.ordering = TransitionSet.ORDERING_TOGETHER
            it.setPathMotion(ArcMotion())
            it.addTransition(ChangeBounds())
            it.addTransition(ChangeTransform())
            it.addTransition(ChangeImageTransform())
        }
    }
}