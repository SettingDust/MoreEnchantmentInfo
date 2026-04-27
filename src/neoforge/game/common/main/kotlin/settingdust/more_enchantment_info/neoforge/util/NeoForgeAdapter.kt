package settingdust.more_enchantment_info.neoforge.util

import net.minecraft.server.packs.resources.PreparableReloadListener
import net.neoforged.api.distmarker.Dist
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

interface NeoForgeAdapter {
    companion object : NeoForgeAdapter by ServiceLoaderUtil.findService()

    val dist: Dist

    fun createReloadListener(): PreparableReloadListener
}