package settingdust.more_enchantment_info.util

import net.minecraft.core.Holder
import net.minecraft.world.item.enchantment.Enchantment

interface RecipeViewerAdapter {
    companion object : RecipeViewerAdapter {
        private val services by lazy { ServiceLoaderUtil.findServices<RecipeViewerAdapter>().toList() }

        override fun viewEnchantment(enchantment: Holder<Enchantment>) {
            for (service in services) {
                service.viewEnchantment(enchantment)
            }
        }
    }

    fun viewEnchantment(enchantment: Holder<Enchantment>)
}
