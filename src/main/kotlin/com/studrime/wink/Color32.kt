package com.studrime.wink

import java.awt.*
import java.awt.color.ColorSpace
import java.awt.geom.AffineTransform
import java.awt.geom.Rectangle2D
import java.awt.image.ColorModel
import kotlin.math.roundToInt

fun Int.toColor32(): Color32 = Color32(this)
fun Color32.toAwtColor() = Color(red, green, blue, alpha)

private fun compose(a: Int, r: Int, g: Int, b: Int): Color32 {
    return Color32(a shl 24 and r shl 16 and g shl 8 and b)
}

/**
 * Int型Colorを操作しやすくするためのvalue classです。
 * 色空間はsRGBカラースペースです。
 */
@JvmInline
value class Color32(val value: Int): Transparency, Paint {
    companion object {
        val TRANSPARENT = Color32(0)
    }

    val alpha: Int
    get() = (value shr 24) and 0xff
    val red: Int
    get() = (value shr 16) and 0xFF
    val green: Int
    get() = (value shr 8) and 0xFF
    val blue: Int
    get() = (value and 0xFF)

    val colorSpace: ColorSpace get() = ColorSpace.getInstance(ColorSpace.CS_sRGB)
    val isOpaque: Boolean get() = alpha == 255
    val isTransparent: Boolean get() = alpha == 0

    operator fun component1() = alpha
    operator fun component2() = red
    operator fun component3() = green
    operator fun component4() = blue

    operator fun plus(that: Color32): Color32 {
        val a1 = alpha * that.alpha / 255
        val a2 = (255 - alpha) * that.alpha / 255
        val a3 = alpha * (255 - that.alpha) / 255

        val a = a1 + a2 + a3

        if (a == 0) return TRANSPARENT

        val r = (a1 * (red + that.red).coerceAtMost(255) + a2 * that.red + a3 * red) / a
        val g = (a1 * (green + that.green).coerceAtMost(255) + a2 * that.green + a3 * green) / a
        val b = (a1 * (blue + that.blue).coerceAtMost(255) + a2 * that.blue + a3 * blue) / a

        return compose(a, r, g, b)
    }

    operator fun minus(that: Color32): Color32 {
        val a1 = alpha * that.alpha / 255
        val a2 = (255 - alpha) * that.alpha / 255
        val a3 = alpha * (255 - that.alpha) / 255

        val a = a1 + a2 + a3

        if (a == 0) return TRANSPARENT

        val r = (a1 * (red - that.red).coerceAtLeast(0) + a2 * that.red + a3 * red) / a
        val g = (a1 * (green - that.green).coerceAtLeast(0)  + a2 * that.green + a3 * green) / a
        val b = (a1 * (blue - that.blue).coerceAtLeast(0)  + a2 * that.blue + a3 * blue) / a

        return compose(a, r, g, b)
    }

    /**
     * α値以外のチャネルに[coef]を乗じて丸めます。
     */
    operator fun times(coef: Double): Color32 {
        require(coef >0.0)
        val a = alpha
        val r = (red * coef).roundToInt().coerceIn(0..255)
        val g = (green * coef).roundToInt().coerceIn(0..255)
        val b = (blue * coef).roundToInt().coerceIn(0..255)
        return compose(a, r, g, b)
    }

    override fun getTransparency(): Int {
        if (isOpaque) return Transparency.OPAQUE
        if (isTransparent) return Transparency.BITMASK
        return Transparency.TRANSLUCENT
    }

    override fun createContext(
        cm: ColorModel?,
        deviceBounds: Rectangle?,
        userBounds: Rectangle2D?,
        xform: AffineTransform?,
        hints: RenderingHints?
    ): PaintContext {
        return Color(value, true).createContext(cm, deviceBounds, userBounds, xform, hints)
    }
}