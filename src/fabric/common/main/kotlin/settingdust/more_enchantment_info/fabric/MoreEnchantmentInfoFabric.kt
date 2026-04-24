package settingdust.more_enchantment_info.fabric

import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Entrypoint

object MoreEnchantmentInfoFabric {
    init {
        MoreEnchantmentInfo
        Entrypoint.construct()
    }

    fun init() {
        Entrypoint.init()
    }

    fun clientInit() {
        Entrypoint.clientInit()
    }
}