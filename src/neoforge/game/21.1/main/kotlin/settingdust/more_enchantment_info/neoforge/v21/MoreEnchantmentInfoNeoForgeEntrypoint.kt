package settingdust.more_enchantment_info.neoforge.v21

import dev.nyon.klf.MOD_BUS
import net.minecraft.SharedConstants
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.util.Entrypoint

class MoreEnchantmentInfoNeoForgeEntrypoint : Entrypoint {
    init {
        check(SharedConstants.getCurrentVersion().name.startsWith("1.21")) {
            "Need to run on 1.21.x"
        }
    }

    override fun construct() {
        MOD_BUS.addListener<RegisterClientReloadListenersEvent> { event ->
            event.registerReloadListener(NeoForgeAdapter.createReloadListener())
        }
    }
}