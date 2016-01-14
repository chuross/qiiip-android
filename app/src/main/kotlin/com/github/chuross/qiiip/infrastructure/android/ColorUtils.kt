package com.github.chuross.qiiip.infrastructure.android

import android.support.v4.graphics.ColorUtils

class ColorUtils {

    private constructor()

    companion object {

        fun changeLuminanceColor(color: Int, rate: Float): Int {
            if (rate !in 0..1) throw IllegalArgumentException("range must be 0..1")
            val hsl = FloatArray(3)
            ColorUtils.colorToHSL(color, hsl)
            return ColorUtils.HSLToColor(FloatArray(3).apply {
                set(0, hsl[0])
                set(1, hsl[1])
                set(2, rate)
            })
        }

    }

}