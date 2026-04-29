package settingdust.more_enchantment_info.v26

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.SpriteLoader
import net.minecraft.client.renderer.texture.TextureAtlas
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.resources.Identifier
import net.minecraft.server.packs.metadata.MetadataSectionType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.v26.util.toNativeIdentifier
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executor

object MoreEnchantmentInfoAtlasManager : PreparableReloadListener, AutoCloseable {
    private val PENDING_STITCH: PreparableReloadListener.StateKey<PendingStitchResults> =
        PreparableReloadListener.StateKey()
    private val textureManager: TextureManager = Minecraft.getInstance().textureManager
    private val config: Config = Config(
        MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION.toNativeIdentifier(),
        MoreEnchantmentInfo.SPRITE_TEXTURE_LOCATION.toNativeIdentifier(),
        emptySet()
    )

    private val atlasEntry: AtlasEntry

    init {
        val atlasTextureId = MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION.toNativeIdentifier()
        val atlas = TextureAtlas(atlasTextureId)
        textureManager.register(atlasTextureId, atlas)
        atlasEntry = AtlasEntry(atlas, config)
    }

    fun getAtlas(): TextureAtlas = atlasEntry.atlas

    override fun close() {
    }

    override fun prepareSharedState(state: PreparableReloadListener.SharedState) {
        val preparations = CompletableFuture<SpriteLoader.Preparations>()
        val pendingStitch = PendingStitch(atlasEntry, preparations)
        val readyToUpload = preparations.thenCompose(SpriteLoader.Preparations::readyForUpload)
        state.set(PENDING_STITCH, PendingStitchResults(pendingStitch, readyToUpload))
    }

    override fun reload(
        currentReload: PreparableReloadListener.SharedState,
        taskExecutor: Executor,
        preparationBarrier: PreparableReloadListener.PreparationBarrier,
        reloadExecutor: Executor
    ): CompletableFuture<Void> {
        val pendingStitchResults = currentReload.get(PENDING_STITCH)
        val resourceManager = currentReload.resourceManager()

        val pendingStitch = pendingStitchResults.pendingStitch
        pendingStitch.entry.scheduleLoad(resourceManager, taskExecutor)
            .whenComplete { preparations, throwable ->
                if (preparations != null) {
                    pendingStitch.preparations.complete(preparations)
                } else {
                    pendingStitch.preparations.completeExceptionally(throwable)
                }
            }

        return pendingStitchResults.readyToUpload
            .thenCompose(preparationBarrier::wait)
            .thenAcceptAsync({ pendingStitchResults.joinAndUpload() }, reloadExecutor)
    }

    data class Config(
        val textureId: Identifier,
        val definitionLocation: Identifier,
        val additionalMetadata: Set<MetadataSectionType<*>>
    )

    private data class AtlasEntry(val atlas: TextureAtlas, val config: Config) : AutoCloseable {
        override fun close() {
            atlas.clearTextureData()
        }

        fun scheduleLoad(
            resourceManager: ResourceManager,
            executor: Executor
        ): CompletableFuture<SpriteLoader.Preparations> {
            return SpriteLoader.create(atlas)
                .loadAndStitch(resourceManager, config.definitionLocation, 0, executor, config.additionalMetadata)
        }
    }

    private data class PendingStitch(
        val entry: AtlasEntry,
        val preparations: CompletableFuture<SpriteLoader.Preparations>
    ) {
        fun joinAndUpload() {
            val value = preparations.join()
            entry.atlas.upload(value)
        }
    }

    private class PendingStitchResults(
        val pendingStitch: PendingStitch,
        val readyToUpload: CompletableFuture<*>
    ) {
        fun joinAndUpload() {
            pendingStitch.joinAndUpload()
        }
    }
}