package settingdust.more_enchantment_info.forge

import net.minecraftforge.client.event.RegisterClientReloadListenersEvent
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.util.Entrypoint
import thedarkcolour.kotlinforforge.forge.MOD_BUS

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