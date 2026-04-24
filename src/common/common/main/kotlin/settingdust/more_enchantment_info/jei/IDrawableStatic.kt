package settingdust.more_enchantment_info.jei

import settingdust.more_enchantment_info.util.GuiGraphics

expect interface IDrawableStatic : IDrawable {
    fun draw(
        guiGraphics: GuiGraphics,
        xOffset: Int,
        yOffset: Int,
        maskTop: Int,
        maskBottom: Int,
        maskLeft: Int,
        maskRight: Int
    )

    override fun getWidth(): Int

    override fun getHeight(): Int

    override fun draw(guiGraphics: GuiGraphics, xOffset: Int, yOffset: Int)
}
