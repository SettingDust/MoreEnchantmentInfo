package settingdust.more_enchantment_info.neoforge.v26

import dev.nyon.klf.MOD_BUS
import net.minecraft.SharedConstants
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.util.Entrypoint

class MoreEnchantmentInfoNeoForgeEntrypoint : Entrypoint {
    init {
        check(SharedConstants.getCurrentVersion().name().startsWith("26")) {
            "Need to run on 26.x"
        }
    }

    override fun construct() {
        MOD_BUS.addListener<AddClientReloadListenersEvent> { event ->
            event.addListener(MoreEnchantmentInfo.id("sprites"), NeoForgeAdapter.createReloadListener())
        }
    }
}