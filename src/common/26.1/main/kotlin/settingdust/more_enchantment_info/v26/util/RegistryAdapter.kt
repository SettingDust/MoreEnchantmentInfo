package settingdust.more_enchantment_info.v26.util

import net.minecraft.core.Registry
import net.minecraft.core.RegistryAccess
import net.minecraft.resources.ResourceKey
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.util.RegistryAdapter
import kotlin.jvm.optionals.getOrNull

class RegistryAdapter : RegistryAdapter {
    init {
        MinecraftVersion.V261.requireCurrent()
    }

    override fun <T : Any> RegistryAccess.getRegistry(registryKey: ResourceKey<Registry<T>>): Registry<T> =
        lookupOrThrow(registryKey)

    override fun <T : Any> Registry<T>.getIdentifier(value: T): Identifier? = getKey(value)?.toCommonIdentifier()

    override fun <T : Any> Registry<T>.getHolderOrNull(resourceKey: ResourceKey<T>) = get(resourceKey).getOrNull()
}