package settingdust.more_enchantment_info.util

import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.entity.player.Player
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag

interface MinecraftAdapter {
    companion object : MinecraftAdapter by ServiceLoaderUtil.findService()

    fun id(namespace: String, path: String): ResourceLocation

    fun ItemStack.getTooltipLines(player: Player?, flat: TooltipFlag): List<Component>
}