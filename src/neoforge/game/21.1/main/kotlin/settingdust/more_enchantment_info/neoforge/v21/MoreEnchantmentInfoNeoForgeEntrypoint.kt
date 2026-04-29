package settingdust.more_enchantment_info.neoforge.v21

import dev.nyon.klf.MOD_BUS
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.util.Entrypoint
import settingdust.more_enchantment_info.util.MinecraftVersion

class MoreEnchantmentInfoNeoForgeEntrypoint : Entrypoint {
    init {
        MinecraftVersion.V1211.requireCurrent()
        @Suppress("RedundantRequireNotNullCall")
        requireNotNull(MOD_BUS)
    }

    override fun construct() {
        MOD_BUS.addListener<RegisterClientReloadListenersEvent> { event ->
            event.registerReloadListener(NeoForgeAdapter.createReloadListener())
        }
    }
}