package settingdust.more_enchantment_info.v21.util

import net.minecraft.SharedConstants
import settingdust.more_enchantment_info.util.MinecraftVersionNameProvider

class MinecraftVersionNameProvider : MinecraftVersionNameProvider {
    init {
        SharedConstants.getCurrentVersion().name
    }

    override fun currentVersionName(): String = SharedConstants.getCurrentVersion().name
}