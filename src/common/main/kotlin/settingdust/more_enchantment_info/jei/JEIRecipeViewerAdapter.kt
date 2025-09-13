package settingdust.more_enchantment_info.jei

import mezz.jei.api.recipe.RecipeIngredientRole
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.jei.EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT
import settingdust.more_enchantment_info.util.RecipeViewerAdapter

class JEIRecipeViewerAdapter : RecipeViewerAdapter {
    override fun viewEnchantment(enchantment: Enchantment) {
        val focus = JEIMoreEnchantmentInfo.INSTANCE.jeiHelpers.focusFactory.createFocus(
            RecipeIngredientRole.INPUT,
            ENCHANTMENT_INGREDIENT,
            enchantment
        )
        JEIMoreEnchantmentInfo.INSTANCE.jeiRuntime.recipesGui.show(focus)
    }
}