package settingdust.more_enchantment_info.neoforge.v21

import net.minecraft.SharedConstants
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.neoforged.fml.loading.FMLLoader
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.v21.MoreEnchantmentInfoSpriteUploader

class NeoForgeAdapter : NeoForgeAdapter {
    init {
        check(SharedConstants.getCurrentVersion().name.startsWith("1.21")) {
            "Need to run on 1.21.x"
        }
    }

    override val dist = FMLLoader.getDist()!!

    override fun createReloadListener(): PreparableReloadListener {
        return MoreEnchantmentInfoSpriteUploader
    }
}