package com.studrime.wink.geom

import java.awt.Rectangle
import java.awt.Shape
import java.awt.geom.*
import kotlin.math.max
import kotlin.math.min


fun Shape.copy(): Shape = GeneralPath(this)

fun Shape.createIntersection(other: Shape): Shape {
    if (this is Rectangle && other is Rectangle) {
        return intersection(other)
    }

    if (this is Rectangle2D) {
        return intersectRectShape(this, other)
    } else if (other is Rectangle2D) {
        return intersectRectShape(other, this)
    }

    return intersectByArea(this, other)
}

private fun intersectRectShape(rect: Rectangle2D, s: Shape): Shape {
    if (s is Rectangle2D) {
        val minX = max(rect.minX, s.minX)
        val maxX = min(rect.maxX, s.maxX)
        val minY = max(rect.minY, s.minY)
        val maxY = min(rect.maxY, s.maxY)
        return Rectangle2D.Double(minX, minY, maxX - minX, maxY - minY)
    }

    if (s.bounds2D in rect) {
        return s.copy()
    }

    return intersectByArea(rect, s)
}

private fun intersectByArea(s1: Shape, s2: Shape): Shape {
    val a1 = if (s1 is Area) s1 else Area(s1)
    val a2 = if (s2 is Area) s2 else Area(s2)
    a1.intersect(a2)
    return if (a1.isRectangular) a1.bounds2D else a1
}

infix fun Point2D.lineTo(end: Point2D): Line2D = Line2D.Double(x, y, end.x, end.y)
infix fun Line2D.lineTo(to: Point2D): GeneralPath = GeneralPath().apply {
    moveTo(x1, y1)
    lineTo(x2, y2)
    lineTo(to.x, to.y)
}