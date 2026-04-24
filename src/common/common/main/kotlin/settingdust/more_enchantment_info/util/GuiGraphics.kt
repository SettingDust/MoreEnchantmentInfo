package settingdust.more_enchantment_info.util

import net.minecraft.world.item.ItemStack

expect class GuiGraphics

expect fun GuiGraphics.renderItem(itemStack: ItemStack, x: Int, y: Int)