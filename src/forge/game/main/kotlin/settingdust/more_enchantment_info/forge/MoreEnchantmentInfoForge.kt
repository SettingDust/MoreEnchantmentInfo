package settingdust.more_enchantment_info.forge

import net.minecraftforge.client.event.RegisterClientReloadListenersEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Entrypoint
import dev.nyon.klf.MOD_BUS
import settingdust.more_enchantment_info.v20.MoreEnchantmentInfoSpriteUploader

@Mod(MoreEnchantmentInfo.ID)
object MoreEnchantmentInfoForge {
    init {
        requireNotNull(MoreEnchantmentInfo)
        Entrypoint.construct()
        MOD_BUS.apply {
            addListener<FMLCommonSetupEvent> {
                Entrypoint.init()
            }
            addListener<FMLClientSetupEvent> { Entrypoint.clientInit() }
            addListener<RegisterClientReloadListenersEvent> { event ->
                event.registerReloadListener(MoreEnchantmentInfoSpriteUploader)
            }
        }
    }
}