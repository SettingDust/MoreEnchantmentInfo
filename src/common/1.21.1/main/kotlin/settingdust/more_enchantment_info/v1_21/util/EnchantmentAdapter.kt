package settingdust.more_enchantment_info.v1_21.util

import net.minecraft.core.Holder
import net.minecraft.core.HolderSet
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceLocation
import net.minecraft.tags.TagKey
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

class EnchantmentAdapter : EnchantmentAdapter {
    companion object {
        private object Tags {
            val ENCHANTMENT_CURSE =
                TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace("curse"))
            val ENCHANTMENT_IN_ENCHANTING_TABLE =
                TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace("in_enchanting_table"))
            val ENCHANTMENT_ON_RANDOM_LOOT =
                TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace("on_random_loot"))
            val ENCHANTMENT_TRADEABLE =
                TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace("tradeable"))
            val ENCHANTMENT_TREASURE =
                TagKey.create(Registries.ENCHANTMENT, ResourceLocation.withDefaultNamespace("treasure"))

            val ENCHANTABLE_ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/armor"))
            val ENCHANTABLE_BOW =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/bow"))
            val ENCHANTABLE_CHEST_ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/chest_armor"))
            val ENCHANTABLE_CROSSBOW =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/crossbow"))
            val ENCHANTABLE_DURABILITY =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/durability"))
            val ENCHANTABLE_EQUIPPABLE =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/equippable"))
            val ENCHANTABLE_FISHING =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/fishing"))
            val ENCHANTABLE_FOOT_ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/foot_armor"))
            val ENCHANTABLE_HEAD_ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/head_armor"))
            val ENCHANTABLE_LEG_ARMOR =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/leg_armor"))
            val ENCHANTABLE_MINING =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/mining"))
            val ENCHANTABLE_MINING_LOOT =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/mining_loot"))
            val ENCHANTABLE_SHARP_WEAPON =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/sharp_weapon"))
            val ENCHANTABLE_SWORD =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/sword"))
            val ENCHANTABLE_TRIDENT =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/trident"))
            val ENCHANTABLE_VANISHING =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/vanishing"))
            val ENCHANTABLE_WEAPON =
                TagKey.create(Registries.ITEM, ResourceLocation.withDefaultNamespace("enchantable/weapon"))
        }

        val CATEGORY_TO_TEXTURE = mapOf(
            Tags.ENCHANTABLE_ARMOR to CATEGORY_ARMOR,
            Tags.ENCHANTABLE_FOOT_ARMOR to CATEGORY_ARMOR_FEET,
            Tags.ENCHANTABLE_HEAD_ARMOR to CATEGORY_ARMOR_HEAD,
            Tags.ENCHANTABLE_LEG_ARMOR to CATEGORY_ARMOR_LEGS,
            Tags.ENCHANTABLE_CHEST_ARMOR to CATEGORY_ARMOR_CHEST,
            Tags.ENCHANTABLE_BOW to CATEGORY_BOW,
            Tags.ENCHANTABLE_CROSSBOW to CATEGORY_CROSSBOW,
            Tags.ENCHANTABLE_DURABILITY to CATEGORY_BREAKABLE,
            Tags.ENCHANTABLE_MINING to CATEGORY_DIGGER,
            Tags.ENCHANTABLE_MINING_LOOT to CATEGORY_DIGGER,
            Tags.ENCHANTABLE_SHARP_WEAPON to CATEGORY_WEAPON,
            Tags.ENCHANTABLE_SWORD to CATEGORY_WEAPON,
            Tags.ENCHANTABLE_TRIDENT to CATEGORY_TRIDENT,
            Tags.ENCHANTABLE_VANISHING to CATEGORY_VANISHABLE,
            Tags.ENCHANTABLE_EQUIPPABLE to CATEGORY_WEARABLE,
            Tags.ENCHANTABLE_WEAPON to CATEGORY_WEAPON,
            Tags.ENCHANTABLE_FISHING to CATEGORY_FISHING_ROD
        )
    }

    override val Enchantment.descriptionPrefix: String
        get() = "enchantment.${key!!.namespace}.${key!!.path}"
    override val Enchantment.name: Component
        get() = description()

    override fun Enchantment.isCompatibleWith(other: Enchantment) = Enchantment.areCompatible(holder!!, other.holder!!)

    override val Enchantment.isLootable: Boolean
        get() = holder!!.`is`(Tags.ENCHANTMENT_ON_RANDOM_LOOT)
    override val Enchantment.isTreasure: Boolean
        get() = holder!!.`is`(Tags.ENCHANTMENT_TREASURE)
    override val Enchantment.isInTable: Boolean
        get() = holder!!.`is`(Tags.ENCHANTMENT_IN_ENCHANTING_TABLE)
    override val Enchantment.isCurse: Boolean
        get() = holder!!.`is`(Tags.ENCHANTMENT_CURSE)
    override val Enchantment.isTradeable: Boolean
        get() = holder!!.`is`(Tags.ENCHANTMENT_TRADEABLE)
    override val Enchantment.weight: Int
        get() = weight
    override val Enchantment.supportedCategories: List<ResourceLocation>
        get() =  definition().supportedItems()
            .filterIsInstance<HolderSet.Named<Item>>()
            .mapNotNull { CATEGORY_TO_TEXTURE[it.key()] }

    override fun EnchantmentInstance(enchantment: Holder<Enchantment>, level: Int) =
        net.minecraft.world.item.enchantment.EnchantmentInstance(enchantment, level)
}