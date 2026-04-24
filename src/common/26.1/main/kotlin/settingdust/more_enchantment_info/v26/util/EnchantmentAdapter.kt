package settingdust.more_enchantment_info.v26.util

import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.network.chat.Component
import net.minecraft.tags.EnchantmentTags
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.enchantment.Enchantment
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
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.holder
import settingdust.more_enchantment_info.util.EnchantmentAdapter.Companion.key
import settingdust.more_enchantment_info.util.Identifier

class EnchantmentAdapter : EnchantmentAdapter {
    companion object {
        val CATEGORY_TO_TEXTURE = mapOf(
            ItemTags.ARMOR_ENCHANTABLE to CATEGORY_ARMOR,
            ItemTags.FOOT_ARMOR_ENCHANTABLE to CATEGORY_ARMOR_FEET,
            ItemTags.HEAD_ARMOR_ENCHANTABLE to CATEGORY_ARMOR_HEAD,
            ItemTags.LEG_ARMOR_ENCHANTABLE to CATEGORY_ARMOR_LEGS,
            ItemTags.CHEST_ARMOR_ENCHANTABLE to CATEGORY_ARMOR_CHEST,
            ItemTags.BOW_ENCHANTABLE to CATEGORY_BOW,
            ItemTags.CROSSBOW_ENCHANTABLE to CATEGORY_CROSSBOW,
            ItemTags.DURABILITY_ENCHANTABLE to CATEGORY_BREAKABLE,
            ItemTags.MINING_ENCHANTABLE to CATEGORY_DIGGER,
            ItemTags.MINING_LOOT_ENCHANTABLE to CATEGORY_DIGGER,
            ItemTags.SHARP_WEAPON_ENCHANTABLE to CATEGORY_WEAPON,
            ItemTags.SWEEPING_ENCHANTABLE to CATEGORY_WEAPON,
            ItemTags.TRIDENT_ENCHANTABLE to CATEGORY_TRIDENT,
            ItemTags.VANISHING_ENCHANTABLE to CATEGORY_VANISHABLE,
            ItemTags.EQUIPPABLE_ENCHANTABLE to CATEGORY_WEARABLE,
            ItemTags.WEAPON_ENCHANTABLE to CATEGORY_WEAPON,
            ItemTags.FISHING_ENCHANTABLE to CATEGORY_FISHING_ROD,
        )
    }

    override val Enchantment.descriptionPrefix: String
        get() = "enchantment.${key!!.getNamespace()}.${key!!.getPath()}"
    override val Enchantment.name: Component
        get() = description()

    override fun Enchantment.isCompatibleWith(other: Enchantment) = Enchantment.areCompatible(holder!!, other.holder!!)

    override val Enchantment.isLootable: Boolean
        get() = holder!!.`is`(EnchantmentTags.ON_RANDOM_LOOT)
    override val Enchantment.isTreasure: Boolean
        get() = holder!!.`is`(EnchantmentTags.TREASURE)
    override val Enchantment.isInTable: Boolean
        get() = holder!!.`is`(EnchantmentTags.IN_ENCHANTING_TABLE)
    override val Enchantment.isCurse: Boolean
        get() = holder!!.`is`(EnchantmentTags.CURSE)
    override val Enchantment.isTradeable: Boolean
        get() = holder!!.`is`(EnchantmentTags.TRADEABLE)
    override val Enchantment.weight: Int
        get() = weight
    override val Enchantment.supportedCategories: List<Identifier>
        get() = definition().supportedItems()
            .filterIsInstance<HolderSet.Named<Item>>()
            .mapNotNull { CATEGORY_TO_TEXTURE[it.key()] }

    override fun EnchantmentInstance(enchantment: Holder<Enchantment>, level: Int) =
        net.minecraft.world.item.enchantment.EnchantmentInstance(enchantment, level)
}
