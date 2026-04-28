package settingdust.more_enchantment_info.v20.jei

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import mezz.jei.api.gui.drawable.IDrawableStatic
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.renderer.GameRenderer
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.jei.DrawableSprite
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.v20.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.v20.util.toNativeIdentifier

class DrawableSpriteDrawable(
    location: Identifier,
    width: Int,
    height: Int,
    paddingTop: Int = 0,
    paddingBottom: Int = 0,
    paddingLeft: Int = 0,
    paddingRight: Int = 0,
) : IDrawableStatic {
    private val sprite = DrawableSprite(location, width, height, paddingTop, paddingBottom, paddingLeft, paddingRight)

    override fun getWidth() = sprite.displayWidth
    override fun getHeight() = sprite.displayHeight

    override fun draw(guiGraphics: GuiGraphics, xOffset: Int, yOffset: Int) =
        draw(guiGraphics, xOffset, yOffset, 0, 0, 0, 0)

    override fun draw(
        guiGraphics: GuiGraphics,
        xOffset: Int,
        yOffset: Int,
        maskTop: Int,
        maskBottom: Int,
        maskLeft: Int,
        maskRight: Int,
    ) {
        val atlasSprite = MoreEnchantmentInfoSpriteUploader.getSprite(sprite.location) ?: return
        val data = sprite.computeRenderData(
            xOffset, yOffset, maskTop, maskBottom, maskLeft, maskRight,
            atlasSprite.u0, atlasSprite.u1, atlasSprite.v0, atlasSprite.v1,
        )
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderTexture(0, MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION.toNativeIdentifier())
        val tesselator = Tesselator.getInstance()
        val bufferBuilder = tesselator.builder
        bufferBuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
        val matrix = guiGraphics.pose().last().pose()
        bufferBuilder.vertex(matrix, data.x.toFloat(), (data.y + data.height).toFloat(), 0f).uv(data.minU, data.maxV).endVertex()
        bufferBuilder.vertex(matrix, (data.x + data.width).toFloat(), (data.y + data.height).toFloat(), 0f).uv(data.maxU, data.maxV).endVertex()
        bufferBuilder.vertex(matrix, (data.x + data.width).toFloat(), data.y.toFloat(), 0f).uv(data.maxU, data.minV).endVertex()
        bufferBuilder.vertex(matrix, data.x.toFloat(), data.y.toFloat(), 0f).uv(data.minU, data.minV).endVertex()
        tesselator.end()
    }
}
