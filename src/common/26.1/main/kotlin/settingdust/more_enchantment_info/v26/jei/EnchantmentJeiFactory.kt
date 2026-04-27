package settingdust.more_enchantment_info.v26.jei

import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.recipe.RecipeIngredientRole
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import net.minecraft.client.Minecraft
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.resources.Identifier
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.jei.EnchantmentJeiFactory
import kotlin.streams.toList

class EnchantmentJeiFactory : EnchantmentJeiFactory {
    private lateinit var jeiHelpers: IJeiHelpers
    private lateinit var jeiRuntime: IJeiRuntime

    private val registry by lazy {
        Minecraft.getInstance().level!!.registryAccess().lookupOrThrow(Registries.ENCHANTMENT)
    }

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime, jeiHelpers: IJeiHelpers) {
        this.jeiRuntime = jeiRuntime
        this.jeiHelpers = jeiHelpers
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        registration.addRecipeCategories(EnchantmentRecipeCategory(registration.jeiHelpers.guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        registration.addRecipes(EnchantmentRecipeCategory.TYPE, registry.listElements().toList() as List<Holder<Enchantment>>)
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        val codec = registry.holderByNameCodec()
        registration.register(
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            registry.listElements().toList() as List<Holder<Enchantment>>,
            EnchantmentIngredientHelper,
            EnchantmentIngredientRenderer,
            codec
        )
    }

    override fun viewEnchantment(enchantment: Holder<Enchantment>) {
        val focus = jeiHelpers.focusFactory.createFocus(
            RecipeIngredientRole.INPUT,
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            enchantment
        )
        jeiRuntime.recipesGui.show(focus)
    }
}
