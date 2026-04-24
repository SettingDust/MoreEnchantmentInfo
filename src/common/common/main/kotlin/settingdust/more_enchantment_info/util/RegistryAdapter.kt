package settingdust.more_enchantment_info.util

import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.RegistryAccess
import net.minecraft.resources.ResourceKey

interface RegistryAdapter {
    companion object : RegistryAdapter by ServiceLoaderUtil.findService<RegistryAdapter>()

    fun <T : Any> RegistryAccess.registryOrThrow(registryKey: ResourceKey<Registry<T>>): Registry<T>

    fun <T : Any> Registry<T>.getIdentifier(value: T): Identifier?

    fun <T : Any> Registry<T>.getHolderOrNull(resourceKey: ResourceKey<T>): Holder<T>?
}
