package settingdust.more_enchantment_info.v1_20.util

import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import settingdust.more_enchantment_info.util.MinecraftAdapter

class MinecraftAdapter : MinecraftAdapter {
    override fun id(namespace: String, path: String) = ResourceLocation(namespace, path)
    override fun ItemStack.getTooltipLines(player: Player?, flat: TooltipFlag) = getTooltipLines(player, flat)
}