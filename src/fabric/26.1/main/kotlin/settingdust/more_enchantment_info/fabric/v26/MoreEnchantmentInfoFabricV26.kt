package settingdust.more_enchantment_info.fabric.v26

import net.fabricmc.fabric.api.resource.v1.ResourceLoader
import net.minecraft.client.Minecraft
import net.minecraft.server.packs.PackType
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.v26.MoreEnchantmentInfoAtlasManager
import settingdust.more_enchantment_info.util.Entrypoint

class MoreEnchantmentInfoFabricV26 : Entrypoint {
    override fun clientInit() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(MoreEnchantmentInfo.id("sprite_uploader"), MoreEnchantmentInfoAtlasManager)
    }
}