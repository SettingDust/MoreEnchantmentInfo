package settingdust.more_enchantment_info.v21.jei

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder
import mezz.jei.api.gui.builder.ITooltipBuilder
import mezz.jei.api.gui.drawable.IDrawable
import mezz.jei.api.gui.ingredient.IRecipeSlotsView
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder
import mezz.jei.api.helpers.IGuiHelper
import mezz.jei.api.recipe.IFocusGroup
import mezz.jei.api.recipe.RecipeIngredientRole
import mezz.jei.api.recipe.RecipeType
import mezz.jei.api.recipe.category.IRecipeCategory
import net.minecraft.ChatFormatting
import net.minecraft.client.Minecraft
import net.minecraft.core.Holder
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.enchantment.Enchantment
import org.joml.Vector4i
import org.joml.Vector4ic
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites
import settingdust.more_enchantment_info.v21.jei.DrawableSpriteDrawable
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.EnchantmentInstance
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.description
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isCompatibleWith
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isCurse
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isInTable
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isLootable
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isTradeable
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.isTreasure
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.name
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.raritySprite
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.supportedCategories
import kotlin.jvm.optionals.getOrNull

class EnchantmentRecipeCategory(private val guiHelper: IGuiHelper) : IRecipeCategory<Holder<Enchantment>> {
    companion object {
        @Suppress("UNCHECKED_CAST")
        val TYPE = RecipeType.create(
            MoreEnchantmentInfo.ID,
            "enchantment",
            Holder::class.java
        ) as RecipeType<Holder<Enchantment>>

        private const val ENCHANTMENT = "ENCHANTMENT"
        private const val APPLICABLE = "APPLICABLE"
        private const val EXCLUSION = "EXCLUSION"
        private const val BASE_HEIGHT = 60
    }

    private val tooltips: MutableMap<Holder<Enchantment>, MutableMap<Vector4ic, () -> Component>> = mutableMapOf()

    override fun getRecipeType() = TYPE
    override fun getTitle() = Items.ENCHANTED_BOOK.getName(Items.ENCHANTED_BOOK.defaultInstance)
    override fun getIcon() = guiHelper.createDrawableItemLike(Items.ENCHANTED_BOOK)
    override fun getWidth() = 144
    override fun getHeight() = BASE_HEIGHT

    override fun getRegistryName(recipe: Holder<Enchantment>) = recipe.unwrapKey().getOrNull()?.location()

    override fun setRecipe(builder: IRecipeLayoutBuilder, recipe: Holder<Enchantment>, focuses: IFocusGroup) {
        val enchantment = recipe.value()

        builder.addInvisibleIngredients(RecipeIngredientRole.INPUT)
            .addIngredient(EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT, recipe)

        builder.addInputSlot(1, 1)
            .setSlotName(ENCHANTMENT)
            .addItemStacks(buildList {
                for (i in (enchantment.minLevel..enchantment.maxLevel)) {
                    add(EnchantedBookItem.createForEnchantment(EnchantmentInstance(recipe, i)))
                }
            })
            .setStandardSlotBackground()

        val applicable =
            Ingredient.of(BuiltInRegistries.ITEM.stream().filter { enchantment.canEnchant(it.defaultInstance) }
                .map { it.defaultInstance })

        builder.addInvisibleIngredients(RecipeIngredientRole.OUTPUT).addIngredients(applicable)

        builder.addOutputSlot(1, 21)
            .setSlotName(APPLICABLE)
            .addRichTooltipCallback { _, tooltip -> tooltip.add(Component.translatable("gui.more_enchantment_info.applicable")) }
            .addIngredients(applicable)
            .setStandardSlotBackground()

        val conflicts = Minecraft.getInstance().level!!.registryAccess().registryOrThrow(Registries.ENCHANTMENT)
            .holders()
            .filter { it.value() != enchantment && !it.value().isCompatibleWith(enchantment) }
            .toList() as List<Holder<Enchantment>>
        if (conflicts.isNotEmpty()) {
            builder.addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 41)
                .setSlotName(EXCLUSION)
                .addRichTooltipCallback { _, tooltip -> tooltip.add(Component.translatable("gui.more_enchantment_info.exclusion")) }
                .addIngredients(EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT, conflicts)
                .setStandardSlotBackground()
        }
    }

    override fun createRecipeExtras(builder: IRecipeExtrasBuilder, recipe: Holder<Enchantment>, focuses: IFocusGroup) {
        val rightWidth = width - 2 - 18
        val enchantment = recipe.value()

        builder.addText(
            enchantment.name.copy().append(" ")
                .append(
                    Component.literal("${enchantment.minLevel}~${enchantment.maxLevel}").withStyle(ChatFormatting.BLUE)
                ),
            rightWidth, 10
        ).setColor(ChatFormatting.WHITE.color!!).setPosition(20, 0).setShadow(true)

        val description = enchantment.description(enchantment.maxLevel)
        if (description != null) {
            builder.addText(description, rightWidth, 40)
                .setPosition(20, 21)
                .setLineSpacing(1)
                .setColor(ChatFormatting.DARK_GRAY.color!!)
        }

        val propertiesTop = 10
        var size = 0

        fun IRecipeExtrasBuilder.addProperty(icon: IDrawable, component: () -> Component) {
            val x = (size % 12) * 10 + 20
            val y = propertiesTop + (size / 12) * 10
            size++
            addDrawable(icon, x, y)
            tooltips.getOrPut(recipe) { mutableMapOf() }[Vector4i(x, y, 8, 8)] = component
        }

        for (category in enchantment.supportedCategories) {
            builder.addProperty(DrawableSpriteDrawable(category, 8, 8, 1, 1, 1, 1)) {
                Component.translatable(
                    "gui.more_enchantment_info.category",
                    Component.translatable(
                        "gui.more_enchantment_info.category.${
                            category.path.removePrefix("category_")
                        }"
                    )
                )
            }
        }

        builder.addProperty(DrawableSpriteDrawable(enchantment.raritySprite, 8, 8, 1, 1, 1, 1)) {
            Component.translatable(
                "gui.more_enchantment_info.rarity",
                Component.translatable(
                    "gui.more_enchantment_info.rarity.${
                        enchantment.raritySprite.path.removePrefix("rarity_")
                    }"
                )
            )
        }

        if (enchantment.isLootable) builder.addProperty(
            DrawableSpriteDrawable(
                MoreEnchantmentInfoSprites.DISCOVERABLE,
                8,
                8,
                1,
                1,
                1,
                1
            )
        ) { Component.translatable("gui.more_enchantment_info.discoverable") }
        if (enchantment.isInTable) builder.addProperty(
            DrawableSpriteDrawable(
                MoreEnchantmentInfoSprites.ENCHANTABLE,
                8,
                8,
                1,
                1,
                1,
                1
            )
        ) { Component.translatable("gui.more_enchantment_info.enchantable") }
        if (enchantment.isTradeable) builder.addProperty(
            DrawableSpriteDrawable(
                MoreEnchantmentInfoSprites.TRADEABLE,
                8,
                8,
                1,
                1,
                1,
                1
            )
        ) { Component.translatable("gui.more_enchantment_info.tradeable") }
        if (enchantment.isTreasure) builder.addProperty(
            DrawableSpriteDrawable(
                MoreEnchantmentInfoSprites.TREASURE,
                8,
                8,
                1,
                1,
                1,
                1
            )
        ) { Component.translatable("gui.more_enchantment_info.treasure") }
        if (enchantment.isCurse) builder.addProperty(
            DrawableSpriteDrawable(
                MoreEnchantmentInfoSprites.CURSE,
                8,
                8,
                1,
                1,
                1,
                1
            )
        ) { Component.translatable("gui.more_enchantment_info.curse") }
    }

    override fun getTooltip(
        tooltip: ITooltipBuilder,
        recipe: Holder<Enchantment>,
        recipeSlotsView: IRecipeSlotsView,
        mouseX: Double,
        mouseY: Double
    ) {
        tooltips[recipe]
            ?.filter { (pos, _) -> pos.x() <= mouseX && mouseX <= pos.x() + pos.z() && pos.y() <= mouseY && mouseY <= pos.y() + pos.w() }
            ?.values
            ?.forEach { tooltip.add(it()) }
    }
}
