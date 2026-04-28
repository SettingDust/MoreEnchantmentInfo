package settingdust.more_enchantment_info.jei

import settingdust.more_enchantment_info.util.Identifier

data class SpriteRenderData(
    val x: Int,
    val y: Int,
    val width: Int,
    val height: Int,
    val minU: Float,
    val maxU: Float,
    val minV: Float,
    val maxV: Float,
)

class DrawableSprite(
    val location: Identifier,
    val width: Int,
    val height: Int,
    val paddingTop: Int = 0,
    val paddingBottom: Int = 0,
    val paddingLeft: Int = 0,
    val paddingRight: Int = 0,
) {
    val displayWidth = width + paddingLeft + paddingRight
    val displayHeight = height + paddingTop + paddingBottom

    fun computeRenderData(
        xOffset: Int,
        yOffset: Int,
        maskTop: Int = 0,
        maskBottom: Int = 0,
        maskLeft: Int = 0,
        maskRight: Int = 0,
        u0: Float,
        u1: Float,
        v0: Float,
        v1: Float,
    ): SpriteRenderData {
        val x = xOffset + maskLeft + paddingLeft
        val y = yOffset + maskTop + paddingTop
        val w = width - maskRight - maskLeft
        val h = height - maskBottom - maskTop
        val uSize = u1 - u0
        val vSize = v1 - v0
        val minU = u0 + uSize * (maskLeft / width.toFloat())
        val minV = v0 + vSize * (maskTop / height.toFloat())
        val maxU = u1 - uSize * (maskRight / width.toFloat())
        val maxV = v1 - vSize * (maskBottom / height.toFloat())
        return SpriteRenderData(x, y, w, h, minU, maxU, minV, maxV)
    }
}
