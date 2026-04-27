package settingdust.more_enchantment_info.neoforge.v21

import dev.nyon.klf.MOD_BUS
import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
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
            addListener<RegisterClientReloadListenersEvent> { event ->
                event.registerReloadListener(NeoForgeAdapter.createReloadListener())
            }
        }
    }
}