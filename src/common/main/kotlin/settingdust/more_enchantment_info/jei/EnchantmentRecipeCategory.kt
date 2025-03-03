package settingdust.more_enchantment_info.jei

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
import net.minecraft.client.resources.language.I18n
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.network.chat.Component
import net.minecraft.world.item.EnchantedBookItem
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentInstance
import org.joml.Vector4i
import org.joml.Vector4ic
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.MoreEnchantmentInfoClient
import kotlin.streams.asStream

class EnchantmentRecipeCategory(val guiHelper: IGuiHelper) : IRecipeCategory<Enchantment> {
    companion object {
        val TYPE = RecipeType.create(MoreEnchantmentInfo.MOD_ID, "enchantment", Enchantment::class.java)!!

        private const val ENCHANTMENT = "ENCHANTMENT"
        private const val APPLICABLE = "APPLICABLE"
        private const val EXCLUSION = "EXCLUSION"

        private const val BASE_HEIGHT = 42
    }

    private var height = BASE_HEIGHT

    private val tooltips: MutableMap<Enchantment, MutableMap<Vector4ic, () -> Component>> = mutableMapOf()

    override fun getRecipeType() = TYPE

    override fun getTitle() = Items.ENCHANTED_BOOK.getName(Items.ENCHANTED_BOOK.defaultInstance)

    override fun getIcon() = guiHelper.createDrawableItemLike(Items.ENCHANTED_BOOK)

    override fun getWidth() = 144

    override fun getHeight() = height

    override fun getRegistryName(recipe: Enchantment) = BuiltInRegistries.ENCHANTMENT.getKey(recipe)

    override fun setRecipe(
        builder: IRecipeLayoutBuilder,
        recipe: Enchantment,
        focuses: IFocusGroup
    ) {
        for (i in (recipe.minLevel..recipe.maxLevel)) {
            builder
                .addInvisibleIngredients(RecipeIngredientRole.INPUT)
                .addItemStack(EnchantedBookItem.createForEnchantment(EnchantmentInstance(recipe, i)))
        }

        builder
            .addInputSlot(1, 1)
            .setSlotName(ENCHANTMENT)
            .addIngredient(EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT, recipe)
            .setStandardSlotBackground()

        val applicable = Ingredient.of(
            BuiltInRegistries.ITEM.asSequence()
                .map { it.defaultInstance }
                .filter { recipe.canEnchant(it) }
                .asStream()
        )

        builder
            .addInvisibleIngredients(RecipeIngredientRole.OUTPUT)
            .addIngredients(applicable)

        builder
            .addOutputSlot(1, 21)
            .setSlotName(APPLICABLE)
            .addRichTooltipCallback { slot, tooltip ->
                tooltip.add(Component.translatable("gui.more_enchantment_info.applicable"))
            }
            .addIngredients(applicable)
            .setStandardSlotBackground()

        val conflicts = BuiltInRegistries.ENCHANTMENT.asSequence()
            .filter { it != recipe && !it.isCompatibleWith(recipe) }
            .toList()
        if (conflicts.isNotEmpty()) {
            height += 20
            builder
                .addSlot(RecipeIngredientRole.RENDER_ONLY, 1, 41)
                .setSlotName(EXCLUSION)
                .addRichTooltipCallback { slot, tooltip ->
                    tooltip.add(Component.translatable("gui.more_enchantment_info.exclusion"))
                }
                .addIngredients(EnchantmentIngredientHelper.ENCHANTMENT_INGREDIENT, conflicts)
                .setStandardSlotBackground()
//
//            builder
//                .addInvisibleIngredients(RecipeIngredientRole.RENDER_ONLY)
//                .addItemStacks(conflicts.map { EnchantedBookItem.createForEnchantment(EnchantmentInstance(it, 1)) })
        }
    }

    override fun createRecipeExtras(builder: IRecipeExtrasBuilder, recipe: Enchantment, focuses: IFocusGroup) {
        val rightWidth = width - 2 - 18
        height = BASE_HEIGHT

        builder
            .addText(
                Component.translatable(recipe.descriptionId)
                    .append(" ")
                    .append(Component.literal("${recipe.minLevel}~${recipe.maxLevel}").withStyle(ChatFormatting.BLUE)),
                rightWidth, 10
            )
            .setColor(ChatFormatting.WHITE.color!!)
            .setPosition(20, 0)
            .setShadow(true)

        if (I18n.exists("${recipe.descriptionId}.desc")) {
            builder.addText(
                Component.translatable("${recipe.descriptionId}.desc"),
                rightWidth,
                if (builder.recipeSlots.findSlotByName(EXCLUSION).isPresent) 40 else 20
            ).setPosition(20, 21)
        }

        val propertiesTop = 10
        var size = 0

        fun IRecipeExtrasBuilder.addProperty(icon: IDrawable, component: () -> Component) {
            val x = (size % 12) * 10 + 20
            val y = propertiesTop + (size / 12) * 10
            size++
            builder.addDrawable(icon, x, y)
            tooltips.getOrPut(recipe) { mutableMapOf() }[Vector4i(x, y, 8, 8)] = component
        }

        MoreEnchantmentInfoClient.Sprites.CATEGORY_TO_TEXTURE[recipe.category]?.let {
            builder.addProperty(DrawableSprite(it, 8, 8, 1, 1, 1, 1)) {
                Component.translatable(
                    "gui.more_enchantment_info.category",
                    Component.translatable(
                        "gui.more_enchantment_info.category.${
                            recipe.category.toString().lowercase()
                        }"
                    )
                )
            }
        }
        MoreEnchantmentInfoClient.Sprites.RARITY_TO_TEXTURE[recipe.rarity]?.let {
            builder.addProperty(DrawableSprite(it, 8, 8, 1, 1, 1, 1)) {
                Component.translatable(
                    "gui.more_enchantment_info.rarity",
                    Component.translatable(
                        "gui.more_enchantment_info.rarity.${
                            recipe.rarity.toString().lowercase()
                        }"
                    )
                )
            }
        }
        if (recipe.isDiscoverable) {
            builder.addProperty(DrawableSprite(MoreEnchantmentInfoClient.Sprites.DISCOVERABLE, 8, 8, 1, 1, 1, 1)) {
                Component.translatable("gui.more_enchantment_info.discoverable")
            }
        }
        if (recipe.isDiscoverable && !recipe.isTreasureOnly) {
            builder.addProperty(DrawableSprite(MoreEnchantmentInfoClient.Sprites.ENCHANTABLE, 8, 8, 1, 1, 1, 1)) {
                Component.translatable("gui.more_enchantment_info.enchantable")
            }
        }
        if (recipe.isTradeable) {
            builder.addProperty(DrawableSprite(MoreEnchantmentInfoClient.Sprites.TRADEABLE, 8, 8, 1, 1, 1, 1)) {
                Component.translatable("gui.more_enchantment_info.tradeable")
            }
        }
        if (recipe.isTreasureOnly) {
            builder.addProperty(DrawableSprite(MoreEnchantmentInfoClient.Sprites.TREASURE, 8, 8, 1, 1, 1, 1)) {
                Component.translatable("gui.more_enchantment_info.treasure")
            }
        }
        if (recipe.isCurse) {
            builder.addProperty(DrawableSprite(MoreEnchantmentInfoClient.Sprites.CURSE, 8, 8, 1, 1, 1, 1)) {
                Component.translatable("gui.more_enchantment_info.curse")
            }
        }
    }

    override fun getTooltip(
        tooltip: ITooltipBuilder,
        recipe: Enchantment,
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