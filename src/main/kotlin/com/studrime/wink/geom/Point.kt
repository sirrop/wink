package com.studrime.wink.geom

import java.awt.Point

operator fun Point.component1() = getX()
operator fun Point.component2() = getY()

