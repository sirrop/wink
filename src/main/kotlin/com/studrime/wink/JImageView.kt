package com.studrime.wink

import com.studrime.wink.java2d.*
import java.awt.Dimension
import java.awt.Graphics
import java.awt.Image
import java.io.Serial
import java.net.URI
import javax.imageio.ImageIO
import javax.swing.JComponent
import kotlin.math.roundToInt

class JImageView internal constructor(
    image: Image? = null
): JComponent() {
    companion object {
        @JvmStatic @Serial
        private val serialVersionUID = 1L

        private fun computeContentSize(image: Image, areaWidth: Int, areaHeight: Int, preserveAspectRatio: Boolean): Dimension {
            return if (preserveAspectRatio) {
                val imageWidth = image.getWidth(null).toDouble()
                val imageHeight = image.getHeight(null).toDouble()
                val imageAspectRatio = imageWidth / imageHeight
                val areaAspectRatio = areaWidth.toDouble() / areaHeight

                // areaの方が横長のとき
                if (imageAspectRatio < areaAspectRatio) {
                    (imageAspectRatio * areaHeight).roundToInt() x areaHeight
                } else {
                    areaWidth x (areaWidth / imageAspectRatio).roundToInt()
                }
            } else {
                areaWidth x areaHeight
            }
        }
    }

    var image: Image? = image
        set(value) {
            field = value
            repaint()
        }

    var preserveAspectRatio: Boolean = false
        set(value) {
            field = value
            repaint()
        }

    var interpolation: Interpolation = Interpolation.BILINEAR
        set(value) {
            field = value
            repaint()
        }

    override fun getPreferredSize(): Dimension {
        val override = super.getPreferredSize()
        if (override == null) {
            val image = this.image
            return if (image == null) 0 x 0 else image.getWidth(null) x image.getHeight(null)
        }
        return override
    }

    override fun paintComponent(g: Graphics) {
        val image = this.image
        if (image != null) {
            g.useAsG2D {
                background = this@JImageView.background
                addRenderingHints(interpolation)

                val (top, left, bottom, right) = border?.getBorderInsets(this@JImageView) ?: Insets()

                val (contentWidth, contentHeight) = computeContentSize(image, width - left - right, height - top - bottom, preserveAspectRatio)

                val x = left
                val y = top
                drawRect(0, 0, width, height)
                drawImage(image, x, y, contentWidth, contentHeight, this@JImageView)
            }
        }
    }
}

fun JImageView(uri: String, op: ComponentOp<JImageView> = {}): JImageView {
    val image = ImageIO.read(URI.create(uri).toURL())
    return JImageView(image, op)
}

fun JImageView(image: Image, op: ComponentOp<JImageView> = {}): JImageView {
    return JImageView(image as Image?).apply(op)
}

fun JImageView(op: ComponentOp<JImageView>): JImageView {
    return JImageView().apply(op)
}