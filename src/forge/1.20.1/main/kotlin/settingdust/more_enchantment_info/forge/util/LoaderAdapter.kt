package settingdust.more_enchantment_info.forge.util

import net.minecraftforge.fml.loading.FMLLoader
import net.minecraftforge.fml.loading.LoadingModList
import settingdust.more_enchantment_info.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient = FMLLoader.getDist().isClient

    override fun isModLoaded(modId: String) = LoadingModList.get().getModFileById(modId) != null
}