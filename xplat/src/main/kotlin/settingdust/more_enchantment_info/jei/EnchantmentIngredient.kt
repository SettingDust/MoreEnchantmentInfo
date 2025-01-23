package settingdust.more_enchantment_info.jei

import mezz.jei.api.ingredients.IIngredientHelper
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.api.ingredients.IIngredientType
import mezz.jei.api.ingredients.subtypes.UidContext
import mezz.jei.api.recipe.RecipeIngredientRole
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.resources.language.I18n
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentInstance
import java.util.stream.Stream

object EnchantmentIngredientHelper : IIngredientHelper<Enchantment> {
    val ENCHANTMENT_INGREDIENT = IIngredientType<Enchantment> { Enchantment::class.java }

    internal fun viewEnchantment(enchantments: Enchantment) {
        val focus = JEIMoreEnchantmentInfo.INSTANCE.jeiHelpers.focusFactory.createFocus(
            RecipeIngredientRole.INPUT,
            ENCHANTMENT_INGREDIENT,
            enchantments
        )
        JEIMoreEnchantmentInfo.INSTANCE.jeiRuntime.recipesGui.show(focus)
    }

    override fun getIngredientType() = ENCHANTMENT_INGREDIENT

    override fun getDisplayName(ingredient: Enchantment) = I18n.get(ingredient.descriptionId)

    override fun getUniqueId(
        ingredient: Enchantment,
        context: UidContext
    ) = "enchantment:${BuiltInRegistries.ENCHANTMENT.getKey(ingredient)}"

    override fun getResourceLocation(ingredient: Enchantment) = BuiltInRegistries.ENCHANTMENT.getKey(ingredient)

    override fun copyIngredient(ingredient: Enchantment) = ingredient

    override fun getErrorInfo(ingredient: Enchantment?) = ingredient?.descriptionId ?: "null"

    override fun getTagStream(ingredient: Enchantment) =
        BuiltInRegistries.ENCHANTMENT.getResourceKey(ingredient)
            .flatMap { BuiltInRegistries.ENCHANTMENT.getHolder(it) }
            .map { it.tags() }
            .orElseGet { Stream.empty() }
            .map { it.location }

    override fun getCheatItemStack(ingredient: Enchantment): ItemStack {
        return EnchantedBookItem.createForEnchantment(EnchantmentInstance(ingredient, 1))
    }
}

object EnchantmentIngredientRenderer : IIngredientRenderer<Enchantment> {
    override fun render(
        guiGraphics: GuiGraphics,
        ingredient: Enchantment
    ) {
        guiGraphics.renderItem(EnchantmentIngredientHelper.getCheatItemStack(ingredient), 0, 0)
    }

    @Suppress("removal")
    override fun getTooltip(
        ingredient: Enchantment,
        tooltipFlag: TooltipFlag
    ) = EnchantmentIngredientHelper.getCheatItemStack(ingredient)
        .getTooltipLines(Minecraft.getInstance().player, tooltipFlag)
}