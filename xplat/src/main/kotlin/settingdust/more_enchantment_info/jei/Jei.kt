package settingdust.more_enchantment_info.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import net.minecraft.core.registries.BuiltInRegistries
import settingdust.more_enchantment_info.MoreEnchantmentInfo

@JeiPlugin
class JEIMoreEnchantmentInfo : IModPlugin {
    companion object {
        lateinit var INSTANCE: JEIMoreEnchantmentInfo
            private set
    }

    init {
        INSTANCE = this
    }

    lateinit var jeiHelpers: IJeiHelpers
    lateinit var jeiRuntime: IJeiRuntime

    override fun getPluginUid() = MoreEnchantmentInfo.identifier("enchantment")

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {
        this.jeiRuntime = jeiRuntime
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        jeiHelpers = registration.jeiHelpers
        val guiHelper = jeiHelpers.guiHelper
        registration.addRecipeCategories(EnchantmentRecipeCategory(guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        registration.addRecipes(EnchantmentRecipeCategory.TYPE, buildList {
            BuiltInRegistries.ENCHANTMENT.forEach { enchantment -> add(enchantment) }
        })
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        registration.register(
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            BuiltInRegistries.ENCHANTMENT.toSet(),
            EnchantmentIngredientHelper,
            EnchantmentIngredientRenderer
        )
    }
}