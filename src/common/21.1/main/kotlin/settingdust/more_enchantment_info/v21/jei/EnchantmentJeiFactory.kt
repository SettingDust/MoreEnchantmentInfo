package settingdust.more_enchantment_info.v21.jei

import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import mezz.jei.api.recipe.RecipeIngredientRole
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.jei.EnchantmentJeiFactory

class EnchantmentJeiFactory : EnchantmentJeiFactory {
    private lateinit var jeiHelpers: IJeiHelpers
    private lateinit var jeiRuntime: IJeiRuntime

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime, jeiHelpers: IJeiHelpers) {
        this.jeiRuntime = jeiRuntime
        this.jeiHelpers = jeiHelpers
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        registration.addRecipeCategories(EnchantmentRecipeCategory(registration.jeiHelpers.guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration, enchantments: Collection<Enchantment>) {
        registration.addRecipes(EnchantmentRecipeCategory.TYPE, enchantments.toList())
    }

    override fun registerIngredients(registration: IModIngredientRegistration, enchantments: Collection<Enchantment>) {
        registration.register(
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            enchantments.toSet(),
            EnchantmentIngredientHelper,
            EnchantmentIngredientRenderer
        )
    }

    override fun viewEnchantment(enchantment: Enchantment) {
        val focus = jeiHelpers.focusFactory.createFocus(
            RecipeIngredientRole.INPUT,
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            enchantment
        )
        jeiRuntime.recipesGui.show(focus)
    }
}
