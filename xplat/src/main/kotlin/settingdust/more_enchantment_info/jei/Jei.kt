package settingdust.more_enchantment_info.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import net.minecraft.core.registries.BuiltInRegistries
import settingdust.more_enchantment_info.MoreEnchantmentInfo

@JeiPlugin
class JEIMoreEnchantmentInfo : IModPlugin {
    override fun getPluginUid() = MoreEnchantmentInfo.identifier("enchantment")

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        val jeiHelpers = registration.jeiHelpers
        val guiHelper = jeiHelpers.guiHelper
        registration.addRecipeCategories(EnchantmentRecipeCategory(guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        registration.addRecipes(EnchantmentRecipeCategory.TYPE, buildList {
            BuiltInRegistries.ENCHANTMENT.forEach { enchantment -> add(enchantment) }
        })
    }
}