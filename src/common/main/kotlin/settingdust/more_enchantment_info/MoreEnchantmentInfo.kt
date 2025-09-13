package settingdust.more_enchantment_info

import org.apache.logging.log4j.LogManager
import settingdust.more_enchantment_info.util.MinecraftAdapter
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

object MoreEnchantmentInfo {
    const val MOD_ID = "more_enchantment_info"
    val LOGGER = LogManager.getLogger()

    init {
        ServiceLoaderUtil.defaultLogger = LOGGER
    }

    fun identifier(path: String) = MinecraftAdapter.id(MOD_ID, path)
}