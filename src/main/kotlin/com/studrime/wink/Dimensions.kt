package com.studrime.wink

import java.awt.Dimension
import java.awt.Rectangle
import kotlin.math.roundToInt

operator fun Dimension.component1(): Int = width
operator fun Dimension.component2(): Int = height

operator fun Dimension.plus(o: Dimension): Dimension {
    return (width + o.width) x (o.height + o.height)
}
operator fun Dimension.minus(o: Dimension): Dimension {
    return (width - o.width) x (width - o.height)
}
operator fun <N: Number> Dimension.times(o: N): Dimension {
    return (width * o.toDouble()).roundToInt() x (height * o.toDouble()).roundToInt()
}
operator fun <N: Number> Dimension.div(o: N): Dimension {
    return (width / o.toDouble()).roundToInt() x (width / o.toDouble()).roundToInt()
}

fun Dimension.toRectangle(x: Int = 0, y: Int = 0) = Rectangle(x, y, width, height)

infix fun Int.x(height: Int) = Dimension(this, height)