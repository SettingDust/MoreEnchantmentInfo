package settingdust.more_enchantment_info.jei

import com.mojang.blaze3d.systems.RenderSystem
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.renderer.GameRenderer
import settingdust.more_enchantment_info.util.GuiGraphics
import settingdust.more_enchantment_info.util.Identifier

actual object DrawableSpriteAdapter {
    actual fun render(
        guiGraphics: GuiGraphics,
        texture: Identifier,
        x: Int,
        y: Int,
        height: Int,
        minU: Float,
        maxV: Float,
        width: Int,
        maxU: Float,
        minV: Float
    ) {
        RenderSystem.setShader { GameRenderer.getPositionTexShader() }
        RenderSystem.setShaderTexture(0, texture)

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
}
