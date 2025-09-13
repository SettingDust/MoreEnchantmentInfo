package settingdust.more_enchantment_info.jei

import com.mojang.blaze3d.systems.RenderSystem
import mezz.jei.api.gui.drawable.IDrawableStatic
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import settingdust.more_enchantment_info.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

interface DrawableSpriteAdapter {
    companion object : DrawableSpriteAdapter by ServiceLoaderUtil.findService()

    fun render(
        guiGraphics: GuiGraphics,
        x: Int,
        y: Int,
        height: Int,
        minU: Float,
        maxV: Float,
        width: Int,
        maxU: Float,
        minV: Float
    )
}

class DrawableSprite(
    private val location: ResourceLocation,
    private val width: Int,
    private val height: Int,
    private val paddingTop: Int = 0,
    private val paddingBottom: Int = 0,
    private val paddingLeft: Int = 0,
    private val paddingRight: Int = 0
) : IDrawableStatic {
    override fun draw(
        guiGraphics: GuiGraphics,
        xOffset: Int,
        yOffset: Int,
        maskTop: Int,
        maskBottom: Int,
        maskLeft: Int,
        maskRight: Int
    ) {
        val sprite = MoreEnchantmentInfoSpriteUploader.INSTANCE.getSprite(location)
        val textureWidth = width
        val textureHeight = height

        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderTexture(0, MoreEnchantmentInfoSpriteUploader.LOCATION)

        val x = xOffset + maskLeft + paddingLeft
        val y = yOffset + maskTop + paddingTop
        val width = width - maskRight - maskLeft
        val height = height - maskBottom - maskTop
        val uSize = sprite.u0 - sprite.u1
        val vSize = sprite.v0 - sprite.v1

        val minU = sprite.u0 + uSize * (maskLeft / textureWidth.toFloat())
        val minV = sprite.v0 + vSize * (maskTop / textureHeight.toFloat())
        val maxU = sprite.u1 - uSize * (maskRight / textureWidth.toFloat())
        val maxV = sprite.v1 - vSize * (maskBottom / textureHeight.toFloat())

        DrawableSpriteAdapter.render(guiGraphics, x, y, height, minU, maxV, width, maxU, minV)
    }

    override fun getWidth() = width + paddingLeft + paddingRight

    override fun getHeight() = height + paddingTop + paddingBottom

    override fun draw(guiGraphics: GuiGraphics, xOffset: Int, yOffset: Int) =
        draw(guiGraphics, xOffset, yOffset, 0, 0, 0, 0)
}