package com.studrime.wink

import java.awt.image.BufferedImage

operator fun BufferedImage.get(x: Int, y: Int): Color32 = Color32(getRGB(x, y))
operator fun BufferedImage.set(x: Int, y: Int, color: Color32) {
    setRGB(x, y, color.value)
}