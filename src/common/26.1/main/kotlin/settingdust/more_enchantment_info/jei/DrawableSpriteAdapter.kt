package settingdust.more_enchantment_info.v26.jei

import settingdust.more_enchantment_info.jei.DrawableSpriteAdapter as CommonDrawableSpriteAdapter
import settingdust.more_enchantment_info.util.GuiGraphics
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.util.toNativeIdentifier

class DrawableSpriteAdapter : CommonDrawableSpriteAdapter {
    init {
        MinecraftVersion.V261.requireCurrent()
    }

    override fun render(
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
        guiGraphics.blit(texture.toNativeIdentifier(), x, y, x + width, y + height, minU, maxU, minV, maxV)
    }
}

