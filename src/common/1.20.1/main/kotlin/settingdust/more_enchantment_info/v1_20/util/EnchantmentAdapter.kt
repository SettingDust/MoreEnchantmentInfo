package settingdust.more_enchantment_info.v1_20.util

import net.minecraft.core.Holder
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import net.minecraft.world.item.enchantment.EnchantmentInstance
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_ARMOR
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_ARMOR_CHEST
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_ARMOR_FEET
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_ARMOR_HEAD
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_ARMOR_LEGS
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_BOW
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_BREAKABLE
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_CROSSBOW
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_DIGGER
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_FISHING_ROD
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_TRIDENT
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_VANISHABLE
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_WEAPON
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites.CATEGORY_WEARABLE
import settingdust.more_enchantment_info.util.EnchantmentAdapter

class EnchantmentAdapter : EnchantmentAdapter {
    companion object {
        val CATEGORY_TO_TEXTURE = mapOf(
            EnchantmentCategory.ARMOR to CATEGORY_ARMOR,
            EnchantmentCategory.ARMOR_FEET to CATEGORY_ARMOR_FEET,
            EnchantmentCategory.ARMOR_LEGS to CATEGORY_ARMOR_LEGS,
            EnchantmentCategory.ARMOR_CHEST to CATEGORY_ARMOR_CHEST,
            EnchantmentCategory.ARMOR_HEAD to CATEGORY_ARMOR_HEAD,
            EnchantmentCategory.WEAPON to CATEGORY_WEAPON,
            EnchantmentCategory.DIGGER to CATEGORY_DIGGER,
            EnchantmentCategory.FISHING_ROD to CATEGORY_FISHING_ROD,
            EnchantmentCategory.BREAKABLE to CATEGORY_BREAKABLE,
            EnchantmentCategory.BOW to CATEGORY_BOW,
            EnchantmentCategory.WEARABLE to CATEGORY_WEARABLE,
            EnchantmentCategory.CROSSBOW to CATEGORY_CROSSBOW,
            EnchantmentCategory.TRIDENT to CATEGORY_TRIDENT,
            EnchantmentCategory.VANISHABLE to CATEGORY_VANISHABLE
        )
    }

    override val Enchantment.descriptionPrefix: String
        get() = descriptionId
    override val Enchantment.name: Component
        get() = Component.translatable(descriptionId)

    override fun Enchantment.isCompatibleWith(other: Enchantment) = isCompatibleWith(other)

    override val Enchantment.isLootable: Boolean
        get() = isDiscoverable
    override val Enchantment.isTreasure: Boolean
        get() = isTreasureOnly
    override val Enchantment.isInTable: Boolean
        get() = isDiscoverable && !isTreasure
    override val Enchantment.isCurse: Boolean
        get() = isCurse
    override val Enchantment.isTradeable: Boolean
        get() = isTradeable
    override val Enchantment.weight: Int
        get() = rarity.weight
    override val Enchantment.supportedCategories: List<ResourceLocation>
        get() = buildList {
            CATEGORY_TO_TEXTURE[category]?.let { add(it) }
        }

    override fun EnchantmentInstance(enchantment: Holder<Enchantment>, level: Int) =
        EnchantmentInstance(enchantment.value(), level)
}