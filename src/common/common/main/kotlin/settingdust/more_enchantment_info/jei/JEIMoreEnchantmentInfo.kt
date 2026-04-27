package settingdust.more_enchantment_info.jei

import mezz.jei.api.IModPlugin
import mezz.jei.api.JeiPlugin
import mezz.jei.api.registration.IModIngredientRegistration
import mezz.jei.api.registration.IRecipeCategoryRegistration
import mezz.jei.api.registration.IRecipeRegistration
import mezz.jei.api.runtime.IJeiRuntime
import net.minecraft.client.Minecraft
import net.minecraft.core.registries.Registries
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.RegistryAdapter.Companion.registryOrThrow

@JeiPlugin
class JEIMoreEnchantmentInfo : IModPlugin {
    companion object {
        lateinit var INSTANCE: JEIMoreEnchantmentInfo
            private set
    }

    init {
        INSTANCE = this
    }

    override fun getPluginUid() = MoreEnchantmentInfo.id("enchantment")

    override fun onRuntimeAvailable(jeiRuntime: IJeiRuntime) {
        EnchantmentJeiFactory.onRuntimeAvailable(jeiRuntime, jeiRuntime.jeiHelpers)
    }

    override fun registerCategories(registration: IRecipeCategoryRegistration) {
        EnchantmentJeiFactory.registerCategories(registration)
    }

    override fun registerRecipes(registration: IRecipeRegistration) {
        EnchantmentJeiFactory.registerRecipes(registration)
    }

    override fun registerIngredients(registration: IModIngredientRegistration) {
        val registry = Minecraft.getInstance().level!!.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
        EnchantmentJeiFactory.registerIngredients(registration)
    }
}
