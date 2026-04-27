package settingdust.more_enchantment_info.neoforge.v26

import net.minecraft.SharedConstants
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.neoforged.api.distmarker.Dist
import net.neoforged.fml.loading.FMLLoader
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.v26.MoreEnchantmentInfoAtlasManager

class NeoForgeAdapter : NeoForgeAdapter {
    init {
        check(SharedConstants.getCurrentVersion().name().startsWith("26.1")) {
            "Need to run on 26.1"
        }
    }

    override val dist: Dist = FMLLoader.getCurrent().dist

    override fun createReloadListener(): PreparableReloadListener {
        return MoreEnchantmentInfoAtlasManager
    }
}
