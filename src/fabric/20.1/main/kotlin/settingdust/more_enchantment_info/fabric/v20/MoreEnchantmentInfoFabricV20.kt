package settingdust.more_enchantment_info.fabric.v20

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.v20.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.util.Entrypoint
import java.util.concurrent.Executor

class MoreEnchantmentInfoFabricV20 : Entrypoint {
    override fun clientInit() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(object : IdentifiableResourceReloadListener {
                private val SPRITE_ID = MoreEnchantmentInfo.id("sprite_uploader")

                override fun getFabricId(): ResourceLocation {
                    return SPRITE_ID
                }

                override fun reload(
                    preparationBarrier: PreparableReloadListener.PreparationBarrier,
                    resourceManager: ResourceManager,
                    preparationsProfiler: ProfilerFiller,
                    reloadProfiler: ProfilerFiller,
                    backgroundExecutor: Executor,
                    gameExecutor: Executor
                ) = MoreEnchantmentInfoSpriteUploader.reload(
                    preparationBarrier,
                    resourceManager,
                    preparationsProfiler,
                    reloadProfiler,
                    backgroundExecutor,
                    gameExecutor
                )
            })
    }
}