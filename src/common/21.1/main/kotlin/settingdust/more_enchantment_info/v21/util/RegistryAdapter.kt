package settingdust.more_enchantment_info.v21.util

import net.minecraft.core.Registry
import net.minecraft.core.RegistryAccess
import net.minecraft.resources.ResourceKey
import settingdust.more_enchantment_info.util.RegistryAdapter
import kotlin.jvm.optionals.getOrNull

class RegistryAdapter : RegistryAdapter {
    override fun <T : Any> RegistryAccess.registryOrThrow(registryKey: ResourceKey<Registry<T>>): Registry<T> =
        registryOrThrow(registryKey)

    override fun <T : Any> Registry<T>.getIdentifier(value: T) = getKey(value)

    override fun <T : Any> Registry<T>.getHolderOrNull(resourceKey: ResourceKey<T>) = getHolder(resourceKey).getOrNull()
}