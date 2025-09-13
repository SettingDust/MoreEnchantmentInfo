package settingdust.more_enchantment_info.jei

import mezz.jei.api.ingredients.IIngredientHelper
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.api.ingredients.IIngredientType
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.GuiGraphics
import net.minecraft.client.resources.language.I18n
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.Enchantment
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.EnchantmentInstance
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.holder
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.key
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.nameKey
import settingdust.more_enchantment_info.util.MinecraftAdapter.Companion.getTooltipLines
import java.util.stream.Stream

object EnchantmentIngredientHelper : IIngredientHelper<Enchantment> {
    val ENCHANTMENT_INGREDIENT = IIngredientType { Enchantment::class.java }

    override fun getIngredientType() = ENCHANTMENT_INGREDIENT

    override fun getDisplayName(ingredient: Enchantment) = I18n.get(ingredient.nameKey)

    override fun getUniqueId(
        ingredient: Enchantment,
        context: UidContext
    ) = "enchantment:${ingredient.key}"

    override fun getResourceLocation(ingredient: Enchantment) = ingredient.key

    override fun copyIngredient(ingredient: Enchantment) = ingredient

    override fun getErrorInfo(ingredient: Enchantment?) = ingredient?.nameKey ?: "null"

    override fun getTagStream(ingredient: Enchantment) =
        ingredient.holder?.tags()?.map { it.location() } ?: Stream.empty()

    override fun getCheatItemStack(ingredient: Enchantment): ItemStack {
        return EnchantedBookItem.createForEnchantment(EnchantmentInstance(ingredient.holder!!, 1))
    }
}

object EnchantmentIngredientRenderer : IIngredientRenderer<Enchantment> {
    override fun render(
        guiGraphics: GuiGraphics,
        ingredient: Enchantment
    ) {
        guiGraphics.renderItem(EnchantmentIngredientHelper.getCheatItemStack(ingredient), 0, 0)
    }

    override fun getTooltip(
        ingredient: Enchantment,
        tooltipFlag: TooltipFlag
    ) = EnchantmentIngredientHelper.getCheatItemStack(ingredient)
        .getTooltipLines(Minecraft.getInstance().player, tooltipFlag)
}