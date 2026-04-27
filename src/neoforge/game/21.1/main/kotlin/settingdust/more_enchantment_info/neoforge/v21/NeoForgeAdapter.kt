package settingdust.more_enchantment_info.neoforge.v21

import net.minecraft.server.packs.resources.PreparableReloadListener
import net.neoforged.fml.loading.FMLLoader
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.v21.MoreEnchantmentInfoSpriteUploader

class NeoForgeAdapter : NeoForgeAdapter {
    init {
        MinecraftVersion.V1211.requireCurrent()
    }

    override val dist = FMLLoader.getDist()!!

    override fun createReloadListener(): PreparableReloadListener {
        return MoreEnchantmentInfoSpriteUploader
    }
}