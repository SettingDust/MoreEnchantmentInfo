package settingdust.more_enchantment_info.v20.jei

import mezz.jei.api.ingredients.IIngredientHelper
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.api.ingredients.IIngredientType
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.EnchantmentInstance
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.key
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.name
import java.util.stream.Stream

object EnchantmentIngredientHelper : IIngredientHelper<Holder<Enchantment>> {
    @Suppress("UNCHECKED_CAST")
    val ENCHANTMENT_INGREDIENT = IIngredientType { Holder::class.java } as IIngredientType<Holder<Enchantment>>

    override fun getIngredientType() = ENCHANTMENT_INGREDIENT

    override fun getDisplayName(ingredient: Holder<Enchantment>): String = ingredient.value().name.string

    override fun getUniqueId(ingredient: Holder<Enchantment>, context: UidContext) =
        "enchantment:${ingredient.value().key}"

    override fun getResourceLocation(ingredient: Holder<Enchantment>) = ingredient.value().key!!

    override fun copyIngredient(ingredient: Holder<Enchantment>) = ingredient

    override fun getErrorInfo(ingredient: Holder<Enchantment>?) = ingredient?.value()?.key.toString()

    override fun getTagStream(ingredient: Holder<Enchantment>): Stream<ResourceLocation> =
        ingredient.tags().map { it.location() } ?: Stream.empty()

    override fun getCheatItemStack(ingredient: Holder<Enchantment>): ItemStack {
        return EnchantedBookItem.createForEnchantment(EnchantmentInstance(ingredient, 1))
    }
}

object EnchantmentIngredientRenderer : IIngredientRenderer<Holder<Enchantment>> {
    override fun render(guiGraphics: GuiGraphics, ingredient: Holder<Enchantment>) {
        guiGraphics.renderItem(EnchantmentIngredientHelper.getCheatItemStack(ingredient), 0, 0)
    }

    @Suppress("removal", "OVERRIDE_DEPRECATION")
    override fun getTooltip(ingredient: Holder<Enchantment>, tooltipFlag: TooltipFlag): List<Component> =
        Screen.getTooltipFromItem(Minecraft.getInstance(), EnchantmentIngredientHelper.getCheatItemStack(ingredient))
}
