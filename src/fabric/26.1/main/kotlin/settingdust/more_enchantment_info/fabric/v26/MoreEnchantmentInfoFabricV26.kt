package settingdust.more_enchantment_info.fabric.v26

import net.fabricmc.fabric.api.resource.v1.ResourceLoader
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Entrypoint
import settingdust.more_enchantment_info.util.toNativeIdentifier
import settingdust.more_enchantment_info.v26.MoreEnchantmentInfoAtlasManager
import java.util.concurrent.Executor

class MoreEnchantmentInfoFabricV26 : Entrypoint {
    override fun clientInit() {
        ResourceLoader.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(MoreEnchantmentInfo.id("sprite_uploader").toNativeIdentifier(), object : PreparableReloadListener {
                override fun prepareSharedState(currentReload: PreparableReloadListener.SharedState) =
                    MoreEnchantmentInfoAtlasManager.prepareSharedState(currentReload)

                override fun reload(
                    currentReload: PreparableReloadListener.SharedState,
                    taskExecutor: Executor,
                    preparationBarrier: PreparableReloadListener.PreparationBarrier,
                    reloadExecutor: Executor
                ) = MoreEnchantmentInfoAtlasManager.reload(
                    currentReload,
                    taskExecutor,
                    preparationBarrier,
                    reloadExecutor
                )
            })
    }
}