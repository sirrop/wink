package com.studrime.wink

import java.awt.Insets

operator fun Insets.component1() = top
operator fun Insets.component2() = left
operator fun Insets.component3() = bottom
operator fun Insets.component4() = right

fun Insets(vertical: Int, horizontal: Int) = Insets(vertical, horizontal, vertical, horizontal)
fun Insets(space: Int = 0) = Insets(space, space, space, space)