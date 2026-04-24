package settingdust.more_enchantment_info.jei

import settingdust.more_enchantment_info.util.GuiGraphics

expect interface IDrawable {
    fun getWidth(): Int

    fun getHeight(): Int

    fun draw(guiGraphics: GuiGraphics, xOffset: Int, yOffset: Int)
}