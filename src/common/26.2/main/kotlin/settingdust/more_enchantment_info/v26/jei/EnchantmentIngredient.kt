package settingdust.more_enchantment_info.v26.jei

import mezz.jei.api.ingredients.IIngredientHelper
import mezz.jei.api.ingredients.IIngredientRenderer
import mezz.jei.api.ingredients.IIngredientType
import mezz.jei.api.ingredients.IIngredientTypeWithSubtypes
import mezz.jei.api.ingredients.subtypes.UidContext
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.screens.Screen
import net.minecraft.core.Holder
import net.minecraft.resources.Identifier
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.TooltipFlag
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentHelper
import net.minecraft.world.item.enchantment.EnchantmentInstance
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.key
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.name
import net.minecraft.client.gui.GuiGraphicsExtractor
import settingdust.more_enchantment_info.v26.util.toNativeIdentifier
import java.util.stream.Stream

object EnchantmentIngredientHelper : IIngredientHelper<Holder<Enchantment>> {
    @Suppress("UNCHECKED_CAST")
    val ENCHANTMENT_INGREDIENT = object : IIngredientTypeWithSubtypes<Enchantment, Holder<Enchantment>> {
        override fun getIngredientClass() = Holder::class.java as Class<Holder<Enchantment>>

        override fun getIngredientBaseClass() = Enchantment::class.java

        override fun getBase(holder: Holder<Enchantment>) = holder.value()
    }

    override fun getIngredientType() = ENCHANTMENT_INGREDIENT

    override fun getDisplayName(ingredient: Holder<Enchantment>) = ingredient.value().name.string

    override fun getUid(ingredient: Holder<Enchantment>, context: UidContext): Any = "enchantment:${ingredient.value().key}"

    override fun getIdentifier(ingredient: Holder<Enchantment>) = ingredient.value().key!!.toNativeIdentifier()

    override fun copyIngredient(ingredient: Holder<Enchantment>) = ingredient

    override fun getErrorInfo(ingredient: Holder<Enchantment>?) = ingredient?.value()?.key.toString()

    override fun getTagStream(ingredient: Holder<Enchantment>): Stream<Identifier> =
        ingredient.tags().map { it.location() } ?: Stream.empty()

    override fun getCheatItemStack(ingredient: Holder<Enchantment>): ItemStack =
        EnchantmentHelper.createBook(EnchantmentInstance(ingredient, ingredient.value().maxLevel))
}

object EnchantmentIngredientRenderer : IIngredientRenderer<Holder<Enchantment>> {
    override fun render(guiGraphics: GuiGraphicsExtractor, ingredient: Holder<Enchantment>) {
        guiGraphics.item(EnchantmentIngredientHelper.getCheatItemStack(ingredient), 0, 0)
    }

    override fun getTooltip(ingredient: Holder<Enchantment>, tooltipFlag: TooltipFlag) =
        Screen.getTooltipFromItem(Minecraft.getInstance(), EnchantmentIngredientHelper.getCheatItemStack(ingredient))
}
