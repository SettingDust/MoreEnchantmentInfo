package settingdust.more_enchantment_info

import net.fabricmc.loader.api.FabricLoader
import org.objectweb.asm.tree.ClassNode
import org.spongepowered.asm.mixin.extensibility.IMixinConfigPlugin
import org.spongepowered.asm.mixin.extensibility.IMixinInfo

class MoreEnchantmentMixinPlugin : IMixinConfigPlugin {
    private var mixinPackagePrefix: String? = null

    override fun onLoad(mixinPackage: String?) {
        mixinPackagePrefix = mixinPackage
    }

    override fun getRefMapperConfig(): String? {
        return null
    }

    override fun shouldApplyMixin(targetClassName: String?, mixinClassName: String?): Boolean {
        val relativePackage = mixinPackagePrefix?.let { mixinClassName?.removePrefix(it) } ?: return true
        if (relativePackage.startsWith("jei")) return FabricLoader.getInstance().isModLoaded("jei")
        return true
    }

    override fun acceptTargets(
        myTargets: Set<String?>?,
        otherTargets: Set<String?>?
    ) {
    }

    override fun getMixins(): List<String?>? {
        return null
    }

    override fun preApply(
        targetClassName: String?,
        targetClass: ClassNode?,
        mixinClassName: String?,
        mixinInfo: IMixinInfo?
    ) {
    }

    override fun postApply(
        targetClassName: String?,
        targetClass: ClassNode?,
        mixinClassName: String?,
        mixinInfo: IMixinInfo?
    ) {
    }
}
