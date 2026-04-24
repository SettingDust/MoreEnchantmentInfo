package settingdust.more_enchantment_info

import org.apache.logging.log4j.LogManager
import settingdust.more_enchantment_info.util.MinecraftAdapter
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

object MoreEnchantmentInfo {
    const val ID = "more_enchantment_info"

    val LOGGER = LogManager.getLogger()

    val TEXTURE_ATLAS_LOCATION = MoreEnchantmentInfo.id("textures/atlas/gui.png")
    val SPRITE_TEXTURE_LOCATION = MoreEnchantmentInfo.id("gui")

    fun id(path: String) = MinecraftAdapter.id(ID, path)
}