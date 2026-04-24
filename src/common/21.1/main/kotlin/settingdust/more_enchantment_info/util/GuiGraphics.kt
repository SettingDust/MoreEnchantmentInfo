package settingdust.more_enchantment_info.util

import net.minecraft.world.item.ItemStack

@Suppress("ACTUAL_WITHOUT_EXPECT")
actual typealias GuiGraphics = net.minecraft.client.gui.GuiGraphics

actual fun GuiGraphics.renderItem(itemStack: ItemStack, x: Int, y: Int) {
    renderItem(itemStack, x, y)
}