package settingdust.more_enchantment_info.fabric.util

import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import settingdust.more_enchantment_info.util.LoaderAdapter

class LoaderAdapter : LoaderAdapter {
    override val isClient = FabricLoader.getInstance().environmentType === EnvType.CLIENT

    override fun isModLoaded(modId: String) = FabricLoader.getInstance().isModLoaded(modId)
}