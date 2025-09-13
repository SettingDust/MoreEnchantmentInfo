package settingdust.more_enchantment_info.fabric

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.api.resource.ResourceManagerHelper
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.MoreEnchantmentInfoSpriteUploader
import settingdust.more_enchantment_info.util.Entrypoint
import java.util.concurrent.Executor

object MoreEnchantmentInfoFabric {
    init {
        MoreEnchantmentInfo
        Entrypoint.construct()
    }

    fun init() {
        Entrypoint.init()
    }

    fun clientInit() {
        ResourceManagerHelper.get(PackType.CLIENT_RESOURCES).registerReloadListener(object : IdentifiableResourceReloadListener {
            private val ID = MoreEnchantmentInfo.identifier("sprite_uploader")

            override fun getFabricId(): ResourceLocation {
                return ID
            }

            override fun reload(
                preparationBarrier: PreparableReloadListener.PreparationBarrier,
                resourceManager: ResourceManager,
                profilerFiller: ProfilerFiller,
                profilerFiller2: ProfilerFiller,
                executor: Executor,
                executor2: Executor
            ) = MoreEnchantmentInfoSpriteUploader.INSTANCE.reload(
                preparationBarrier,
                resourceManager,
                profilerFiller,
                profilerFiller2,
                executor,
                executor2
            )
        })
        Entrypoint.clientInit()
    }
 }