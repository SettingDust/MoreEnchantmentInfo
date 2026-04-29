package settingdust.more_enchantment_info.v21.jei

import net.minecraft.core.Holder
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.util.RecipeViewerAdapter

class JEIRecipeViewerAdapter : RecipeViewerAdapter {
    init {
        MinecraftVersion.V1211.requireCurrent()
    }

    override fun viewEnchantment(enchantment: Holder<Enchantment>) {
        JEIMoreEnchantmentInfo.INSTANCE.viewEnchantment(enchantment)
    }
}
