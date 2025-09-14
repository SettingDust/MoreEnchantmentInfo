package settingdust.more_enchantment_info.neoforge

import net.neoforged.fml.common.Mod
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.util.Entrypoint
import thedarkcolour.kotlinforforge.neoforge.forge.MOD_BUS

@Mod(MoreEnchantmentInfo.MOD_ID)
object MoreEnchantmentInfoForge {
    init {
        MoreEnchantmentInfo
        Entrypoint.construct()

        MOD_BUS.apply {
            addListener<FMLCommonSetupEvent> {
                Entrypoint.init()
            }
            addListener<FMLClientSetupEvent> {
                Entrypoint.clientInit()
            }
            addListener<RegisterClientReloadListenersEvent> { event ->
                event.registerReloadListener(MoreEnchantmentInfoSpriteUploader.INSTANCE)
            }
        }
    }
}