package settingdust.more_enchantment_info.v26.jei

import mezz.jei.api.gui.drawable.IDrawableStatic
import net.minecraft.client.gui.GuiGraphicsExtractor
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.jei.DrawableSprite
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.v26.util.toNativeIdentifier
import settingdust.more_enchantment_info.v26.MoreEnchantmentInfoAtlasManager

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

    override fun draw(guiGraphics: GuiGraphicsExtractor, xOffset: Int, yOffset: Int) =
        draw(guiGraphics, xOffset, yOffset, 0, 0, 0, 0)

    override fun draw(
        guiGraphics: GuiGraphicsExtractor,
        xOffset: Int,
        yOffset: Int,
        maskTop: Int,
        maskBottom: Int,
        maskLeft: Int,
        maskRight: Int,
    ) {
        val atlasSprite = MoreEnchantmentInfoAtlasManager.getAtlas().getSprite(sprite.location.toNativeIdentifier()) ?: return
        val data = sprite.computeRenderData(
            xOffset, yOffset, maskTop, maskBottom, maskLeft, maskRight,
            atlasSprite.u0, atlasSprite.u1, atlasSprite.v0, atlasSprite.v1,
        )
        guiGraphics.blit(
            MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION.toNativeIdentifier(),
            data.x, data.y,
            data.x + data.width, data.y + data.height,
            data.minU, data.maxU,
            data.minV, data.maxV,
        )
    }
}
