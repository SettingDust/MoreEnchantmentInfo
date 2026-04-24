package settingdust.more_enchantment_info.jei

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
        guiGraphics.blit(texture, x, y, x + width, y + height, minU, maxU, minV, maxV)
    }
}
