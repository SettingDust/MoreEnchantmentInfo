package settingdust.more_enchantment_info.neoforge.v26.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.v26.jei.JEIMoreEnchantmentInfo
import settingdust.more_enchantment_info.v26.util.toNativeIdentifier

class NeoForgeJEIMoreEnchantmentInfo : IModPlugin {
    private val delegate = JEIMoreEnchantmentInfo()

    override fun getPluginUid() = MoreEnchantmentInfo.id("enchantment").toNativeIdentifier()

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {
        delegate.onRuntimeAvailable(jeiRuntime)
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        delegate.registerCategories(registration)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        delegate.registerRecipes(registration)
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        delegate.registerIngredients(registration)
    }
}
