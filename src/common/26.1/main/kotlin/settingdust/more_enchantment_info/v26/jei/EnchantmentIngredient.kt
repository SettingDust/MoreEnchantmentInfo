package settingdust.more_enchantment_info.v26.jei

import mezz.jei.api.ingredients.IIngredientHelper
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.api.ingredients.IIngredientType
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.holder
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.key
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.name
import settingdust.more_enchantment_info.util.GuiGraphics
import settingdust.more_enchantment_info.util.renderItem
import java.util.stream.Stream

object EnchantmentIngredientHelper : IIngredientHelper<Enchantment> {
    val ENCHANTMENT_INGREDIENT = IIngredientType { Enchantment::class.java }

    override fun getIngredientType() = ENCHANTMENT_INGREDIENT

    override fun getDisplayName(ingredient: Enchantment) = ingredient.name.string

    override fun getUid(ingredient: Enchantment, context: UidContext): Any = "enchantment:${ingredient.key}"

    override fun getIdentifier(ingredient: Enchantment) = ingredient.key!!

    override fun copyIngredient(ingredient: Enchantment) = ingredient

    override fun getErrorInfo(ingredient: Enchantment?) = ingredient?.key.toString()

    override fun getTagStream(ingredient: Enchantment) =
        ingredient.holder?.tags()?.map { it.location() } ?: Stream.empty()

    override fun getCheatItemStack(ingredient: Enchantment): ItemStack = Items.ENCHANTED_BOOK.defaultInstance
}

object EnchantmentIngredientRenderer : IIngredientRenderer<Enchantment> {
    override fun render(guiGraphics: GuiGraphics, ingredient: Enchantment) {
        guiGraphics.renderItem(EnchantmentIngredientHelper.getCheatItemStack(ingredient), 0, 0)
    }

    override fun getTooltip(ingredient: Enchantment, tooltipFlag: TooltipFlag) =
        Screen.getTooltipFromItem(Minecraft.getInstance(), EnchantmentIngredientHelper.getCheatItemStack(ingredient))
}
