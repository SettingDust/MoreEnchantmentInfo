package settingdust.more_enchantment_info.neoforge.v26

import dev.nyon.klf.MOD_BUS
import net.neoforged.neoforge.client.event.AddClientReloadListenersEvent
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.neoforge.util.NeoForgeAdapter
import settingdust.more_enchantment_info.util.Entrypoint
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.util.toNativeIdentifier

class MoreEnchantmentInfoNeoForgeEntrypoint : Entrypoint {
    init {
        MinecraftVersion.V261.requireCurrent()
    }

    override fun construct() {
        MOD_BUS.addListener<AddClientReloadListenersEvent> { event ->
            event.addListener(MoreEnchantmentInfo.id("sprites").toNativeIdentifier(), NeoForgeAdapter.createReloadListener())
        }
    }
}