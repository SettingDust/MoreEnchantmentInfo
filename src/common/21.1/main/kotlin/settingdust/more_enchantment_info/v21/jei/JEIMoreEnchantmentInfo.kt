package settingdust.more_enchantment_info.v21.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.helpers.IJeiHelpers
import mezz.jei.api.recipe.RecipeIngredientRole
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import net.minecraft.client.Minecraft
import net.minecraft.core.Holder
import net.minecraft.core.registries.Registries
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.MinecraftVersion
import settingdust.more_enchantment_info.v21.util.toNativeIdentifier

class JEIMoreEnchantmentInfo : IModPlugin {
    companion object {
        lateinit var INSTANCE: JEIMoreEnchantmentInfo
            private set
    }

    private lateinit var jeiHelpers: IJeiHelpers
    private lateinit var jeiRuntime: IJeiRuntime

    private val registry by lazy {
        Minecraft.getInstance().level!!.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
    }

    init {
        MinecraftVersion.V1211.requireCurrent()
        INSTANCE = this
    }

    override fun getPluginUid() = MoreEnchantmentInfo.id("enchantment").toNativeIdentifier()

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {
        this.jeiRuntime = jeiRuntime
        this.jeiHelpers = jeiRuntime.jeiHelpers
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        registration.addRecipeCategories(EnchantmentRecipeCategory(registration.jeiHelpers.guiHelper))
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        registration.addRecipes(EnchantmentRecipeCategory.TYPE, registry.holders().toList() as List<Holder<Enchantment>>)
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        registration.register(
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            registry.holders().toList() as List<Holder<Enchantment>>,
            EnchantmentIngredientHelper,
            EnchantmentIngredientRenderer,
            registry.holderByNameCodec()
        )
    }

    fun viewEnchantment(enchantment: Holder<Enchantment>) {
        val focus = jeiHelpers.focusFactory.createFocus(
            RecipeIngredientRole.INPUT,
            EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT,
            enchantment
        )
        jeiRuntime.recipesGui.show(focus)
    }
}