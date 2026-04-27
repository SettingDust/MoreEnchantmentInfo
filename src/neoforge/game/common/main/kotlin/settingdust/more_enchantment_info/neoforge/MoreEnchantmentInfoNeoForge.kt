package settingdust.more_enchantment_info.neoforge

import dev.nyon.klf.MOD_BUS
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Entrypoint

@Mod(MoreEnchantmentInfo.ID)
object MoreEnchantmentInfoNeoForge {
    init {
        requireNotNull(MoreEnchantmentInfo)
        Entrypoint.construct()
        MOD_BUS.apply {
            addListener<FMLCommonSetupEvent> {
                Entrypoint.init()
            }
            addListener<FMLClientSetupEvent> {
                Entrypoint.clientInit()
            }
        }
    }
}