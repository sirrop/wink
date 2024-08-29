package com.studrime.wink

import java.awt.Cursor
import java.awt.Image
import java.awt.Point
import java.awt.Toolkit

object Cursors {
    val CROSSHAIR: Cursor get() = Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR)
    val DEFAULT: Cursor get() = Cursor.getDefaultCursor()
    val E_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR)
    val HAND: Cursor get() = Cursor.getPredefinedCursor(Cursor.HAND_CURSOR)
    val MOVE: Cursor get() = Cursor.getPredefinedCursor(Cursor.MOVE_CURSOR)
    val N_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.N_RESIZE_CURSOR)
    val NE_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.NE_RESIZE_CURSOR)
    val NW_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.NW_RESIZE_CURSOR)
    val S_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR)
    val SE_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.SE_RESIZE_CURSOR)
    val SW_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.SW_RESIZE_CURSOR)
    val TEXT: Cursor get() = Cursor.getPredefinedCursor(Cursor.TEXT_CURSOR)
    val W_RESIZE: Cursor get() = Cursor.getPredefinedCursor(Cursor.W_RESIZE_CURSOR)
    val WAIT: Cursor get() = Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR)

    fun create(image: Image, hotspot: Point, name: String) = Toolkit.getDefaultToolkit().createCustomCursor(image, hotspot, name)
}

