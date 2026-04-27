package settingdust.more_enchantment_info.v26.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.util.toNativeIdentifier

class JEIMoreEnchantmentInfo : IModPlugin {
    companion object {
        lateinit var INSTANCE: JEIMoreEnchantmentInfo
            private set
    }

    private val factory = EnchantmentJeiFactory()

    init {
        MinecraftVersion.V261.requireCurrent()
        INSTANCE = this
    }

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