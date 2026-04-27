package settingdust.more_enchantment_info.neoforge.v21.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.toNativeIdentifier
import settingdust.more_enchantment_info.v21.jei.EnchantmentJeiFactory

class NeoForgeJEIMoreEnchantmentInfo : IModPlugin {
    private val factory = EnchantmentJeiFactory()

    override fun getPluginUid() = MoreEnchantmentInfo.id("enchantment").toNativeIdentifier()

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {
        factory.onRuntimeAvailable(jeiRuntime, jeiRuntime.jeiHelpers)
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        factory.registerCategories(registration)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        factory.registerRecipes(registration)
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        factory.registerIngredients(registration)
    }
}
