package settingdust.more_enchantment_info.jei

import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.ServiceLoaderUtil

interface EnchantmentJeiFactory {
    companion object : EnchantmentJeiFactory by ServiceLoaderUtil.findService()

    fun onRuntimeAvailable(jeiRuntime: IJeiRuntime, jeiHelpers: IJeiHelpers)

    fun registerCategories(registration: IRecipeCategoryRegistration)

    fun registerRecipes(registration: IRecipeRegistration, enchantments: Collection<Enchantment>)

    fun registerIngredients(registration: IModIngredientRegistration, enchantments: Collection<Enchantment>)

    fun viewEnchantment(enchantment: Enchantment)
}