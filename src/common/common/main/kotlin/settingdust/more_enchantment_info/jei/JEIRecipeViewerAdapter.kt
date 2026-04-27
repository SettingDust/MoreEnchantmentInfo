package settingdust.more_enchantment_info.jei

import net.minecraft.core.Holder
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.RecipeViewerAdapter

class JEIRecipeViewerAdapter : RecipeViewerAdapter {
    override fun viewEnchantment(enchantment: Holder<Enchantment>) {
        EnchantmentJeiFactory.viewEnchantment(enchantment)
    }
}
