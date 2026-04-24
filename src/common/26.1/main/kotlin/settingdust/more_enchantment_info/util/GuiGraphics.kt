package settingdust.more_enchantment_info.util

import net.minecraft.world.item.ItemStack

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual typealias GuiGraphics = net.minecraft.client.gui.GuiGraphicsExtractor

actual fun GuiGraphics.renderItem(itemStack: ItemStack, x: Int, y: Int) {
    item(itemStack, x, y)
}