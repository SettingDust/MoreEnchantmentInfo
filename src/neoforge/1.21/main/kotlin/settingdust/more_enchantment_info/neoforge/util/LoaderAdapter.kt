package settingdust.more_enchantment_info.neoforge.util

import net.neoforged.fml.loading.FMLLoader
import net.neoforged.fml.loading.LoadingModList
import settingdust.more_enchantment_info.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient = FMLLoader.getDist().isClient

    override fun isModLoaded(modId: String) = LoadingModList.get().getModFileById(modId) != null
}