package com.studrime.wink.java2d

import java.awt.Graphics
import java.awt.Graphics2D

fun <G: Graphics> G.use(op: Graphics.() -> Unit) {
    val g = create()
    g.op()
    g.dispose()
}

fun <G: Graphics> G.useAsG2D(op: Graphics2D.() -> Unit) {
    val g = create() as Graphics2D
    g.op()
    g.dispose()
}

fun <G: Graphics2D> G.addRenderingHints(vararg hints: RenderingHint): G {
    hints.map { it.toRenderingHints() }
        .reduce { acc, h -> acc.apply { add(h) } }
        .run { addRenderingHints(this) }
    return this
}