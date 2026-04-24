package settingdust.more_enchantment_info.v26.util

import net.minecraft.resources.Identifier
import settingdust.more_enchantment_info.util.MinecraftAdapter

class MinecraftAdapter : MinecraftAdapter {
    override fun id(namespace: String, path: String) = Identifier.fromNamespaceAndPath(namespace, path)
}
