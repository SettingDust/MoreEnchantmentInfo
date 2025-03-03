package settingdust.more_enchantment_info.jei

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import mezz.jei.api.gui.drawable.IDrawableStatic
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
import net.minecraft.resources.ResourceLocation
import settingdust.more_enchantment_info.MoreEnchantmentInfoSpriteUploader

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

        val tesselator = Tesselator.getInstance()
        val bufferBuilder = tesselator.builder
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
        val matrix = guiGraphics.pose().last().pose()
        bufferBuilder
            .vertex(matrix, x.toFloat(), y + height.toFloat(), 0f)
            .uv(minU, maxV)
            .endVertex()
        bufferBuilder
            .vertex(matrix, x + width.toFloat(), y + height.toFloat(), 0f)
            .uv(maxU, maxV)
            .endVertex()
        bufferBuilder
            .vertex(matrix, x + width.toFloat(), y.toFloat(), 0f)
            .uv(maxU, minV)
            .endVertex()
        bufferBuilder
            .vertex(matrix, x.toFloat(), y.toFloat(), 0f)
            .uv(minU, minV)
            .endVertex()
        tesselator.end()
    }

    override fun getWidth() = width + paddingLeft + paddingRight

    override fun getHeight() = height + paddingTop + paddingBottom

    override fun draw(guiGraphics: GuiGraphics, xOffset: Int, yOffset: Int) =
        draw(guiGraphics, xOffset, yOffset, 0, 0, 0, 0)
}