package settingdust.more_enchantment_info

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import settingdust.more_enchantment_info.util.Identifier

object MoreEnchantmentInfoSprites {
    val CATEGORY_ARMOR = MoreEnchantmentInfo.id("category_armor")
    val CATEGORY_ARMOR_HEAD = MoreEnchantmentInfo.id("category_armor_head")
    val CATEGORY_ARMOR_CHEST = MoreEnchantmentInfo.id("category_armor_chest")
    val CATEGORY_ARMOR_LEGS = MoreEnchantmentInfo.id("category_armor_legs")
    val CATEGORY_ARMOR_FEET = MoreEnchantmentInfo.id("category_armor_feet")
    val CATEGORY_WEAPON = MoreEnchantmentInfo.id("category_weapon")
    val CATEGORY_DIGGER = MoreEnchantmentInfo.id("category_digger")
    val CATEGORY_FISHING_ROD = MoreEnchantmentInfo.id("category_fishing_rod")
    val CATEGORY_TRIDENT = MoreEnchantmentInfo.id("category_trident")
    val CATEGORY_BREAKABLE = MoreEnchantmentInfo.id("category_breakable")
    val CATEGORY_BOW = MoreEnchantmentInfo.id("category_bow")
    val CATEGORY_WEARABLE = MoreEnchantmentInfo.id("category_wearable")
    val CATEGORY_CROSSBOW = MoreEnchantmentInfo.id("category_crossbow")
    val CATEGORY_VANISHABLE = MoreEnchantmentInfo.id("category_vanishable")

    val CURSE = MoreEnchantmentInfo.id("curse")
    val DISCOVERABLE = MoreEnchantmentInfo.id("discoverable")
    val ENCHANTABLE = MoreEnchantmentInfo.id("enchantable")
    val TRADEABLE = MoreEnchantmentInfo.id("tradable")
    val TREASURE = MoreEnchantmentInfo.id("treasure")

    val RARITY_COMMON = MoreEnchantmentInfo.id("rarity_common")
    val RARITY_UNCOMMON = MoreEnchantmentInfo.id("rarity_uncommon")
    val RARITY_RARE = MoreEnchantmentInfo.id("rarity_rare")
    val RARITY_VERY_RARE = MoreEnchantmentInfo.id("rarity_very_rare")
}

expect fun MoreEnchantmentInfoSprites.getSprite(id: Identifier): TextureAtlasSprite?
