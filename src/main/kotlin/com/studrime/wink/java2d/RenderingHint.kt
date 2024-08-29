package com.studrime.wink.java2d

import java.awt.RenderingHints

sealed interface RenderingHint {
    fun toRenderingHints(): RenderingHints
}

enum class Interpolation: RenderingHint {
    BICUBIC, BILINEAR, NEAREST_NEIGHBOR;

    override fun toRenderingHints(): RenderingHints = when (this) {
        BICUBIC -> RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BICUBIC)
        BILINEAR -> RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR)
        NEAREST_NEIGHBOR -> RenderingHints(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR)
    }
}

enum class ColorRendering: RenderingHint {
    DEFAULT, QUALITY, SPEED;

    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_COLOR_RENDERING
        val value = when (this) {
            DEFAULT -> RenderingHints.VALUE_COLOR_RENDER_DEFAULT
            QUALITY -> RenderingHints.VALUE_COLOR_RENDER_QUALITY
            SPEED -> RenderingHints.VALUE_COLOR_RENDER_SPEED
        }
        return RenderingHints(key, value)
    }
}

enum class Antialiasing: RenderingHint {
    AUTO, ON, OFF;

    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_ANTIALIASING
        val value = when (this) {
            Antialiasing.AUTO -> RenderingHints.VALUE_ANTIALIAS_DEFAULT
            Antialiasing.ON -> RenderingHints.VALUE_ANTIALIAS_ON
            Antialiasing.OFF -> RenderingHints.VALUE_ANTIALIAS_OFF
        }
        return RenderingHints(key, value)
    }
}

enum class AlphaInterpolation: RenderingHint {
    DEFAULT, QUALITY, SPEED;

    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_ALPHA_INTERPOLATION
        val value = when (this) {
            AlphaInterpolation.DEFAULT -> RenderingHints.VALUE_ALPHA_INTERPOLATION_DEFAULT
            AlphaInterpolation.QUALITY -> RenderingHints.VALUE_ALPHA_INTERPOLATION_QUALITY
            AlphaInterpolation.SPEED -> RenderingHints.VALUE_ALPHA_INTERPOLATION_SPEED
        }
        return RenderingHints(key, value)
    }
}

enum class Dithering: RenderingHint {
    DEFAULT, DISABLE, ENABLE;
    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_DITHERING
        val value = when (this) {
            DEFAULT -> RenderingHints.VALUE_DITHER_DEFAULT
            DISABLE -> RenderingHints.VALUE_DITHER_DISABLE
            ENABLE -> RenderingHints.VALUE_DITHER_ENABLE
        }
        return RenderingHints(key, value)
    }
}

enum class FractionalMetrics: RenderingHint {
    DEFAULT, ON, OFF;
    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_FRACTIONALMETRICS
        val value = when (this) {
            DEFAULT -> RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT
            ON -> RenderingHints.VALUE_FRACTIONALMETRICS_ON
            OFF -> RenderingHints.VALUE_FRACTIONALMETRICS_OFF
        }
        return RenderingHints(key, value)
    }
}

enum class Rendering: RenderingHint {
    DEFAULT, QUALITY, SPEED;
    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_FRACTIONALMETRICS
        val value = when (this) {
            DEFAULT -> RenderingHints.VALUE_FRACTIONALMETRICS_DEFAULT
            QUALITY -> RenderingHints.VALUE_RENDER_QUALITY
            SPEED -> RenderingHints.VALUE_RENDER_QUALITY
        }
        return RenderingHints(key, value)
    }
}

enum class StrokeControl: RenderingHint {
    DEFAULT, NORMALIZE, PURE;
    override fun toRenderingHints(): RenderingHints {
        val key = RenderingHints.KEY_STROKE_CONTROL
        val value = when (this) {
            DEFAULT -> RenderingHints.VALUE_STROKE_DEFAULT
            NORMALIZE -> RenderingHints.VALUE_STROKE_NORMALIZE
            PURE -> RenderingHints.VALUE_STROKE_PURE
        }
        return RenderingHints(key, value)
    }
}

fun union(vararg hints: RenderingHint): RenderingHints {
    return hints.map { it.toRenderingHints() }
        .reduce { acc, renderingHints -> renderingHints.apply { add(acc) } }
}