package settingdust.more_enchantment_info.v1_21.jei

import com.mojang.blaze3d.vertex.BufferUploader
import com.mojang.blaze3d.vertex.DefaultVertexFormat
import com.mojang.blaze3d.vertex.Tesselator
import com.mojang.blaze3d.vertex.VertexFormat
import net.minecraft.client.gui.GuiGraphics
import settingdust.more_enchantment_info.jei.DrawableSpriteAdapter

class DrawableSpriteAdapter : DrawableSpriteAdapter {
    override fun render(
        guiGraphics: GuiGraphics,
        x: Int,
        y: Int,
        height: Int,
        minU: Float,
        maxV: Float,
        width: Int,
        maxU: Float,
        minV: Float
    ) {
        val tesselator = Tesselator.getInstance()
        val bufferBuilder = tesselator.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX)
        val matrix = guiGraphics.pose().last().pose()
        bufferBuilder.addVertex(matrix, x.toFloat(), y.toFloat() + height, 0f).setUv(minU, maxV)
        bufferBuilder.addVertex(matrix, x.toFloat() + width, y.toFloat() + height, 0f).setUv(maxU, maxV)
        bufferBuilder.addVertex(matrix, x.toFloat() + width, y.toFloat(), 0f).setUv(maxU, minV)
        bufferBuilder.addVertex(matrix, x.toFloat(), y.toFloat(), 0f).setUv(minU, minV)
        BufferUploader.drawWithShader(bufferBuilder.buildOrThrow())
    }
}