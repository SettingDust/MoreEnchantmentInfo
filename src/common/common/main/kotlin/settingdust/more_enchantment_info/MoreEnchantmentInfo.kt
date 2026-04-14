package settingdust.more_enchantment_info

import org.apache.logging.log4j.LogManager
import settingdust.more_enchantment_info.util.MinecraftAdapter
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

object MoreEnchantmentInfo {
    const val ID = "more_enchantment_info"

    val LOGGER = LogManager.getLogger()

    fun id(path: String) = MinecraftAdapter.id(ID, path)
}