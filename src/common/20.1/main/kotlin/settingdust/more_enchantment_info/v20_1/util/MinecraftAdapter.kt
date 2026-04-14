package settingdust.more_enchantment_info.v20_1.util

import net.minecraft.resources.ResourceLocation
import settingdust.more_enchantment_info.util.MinecraftAdapter

class MinecraftAdapter : MinecraftAdapter {
    override fun id(namespace: String, path: String) = ResourceLocation(namespace, path)
}