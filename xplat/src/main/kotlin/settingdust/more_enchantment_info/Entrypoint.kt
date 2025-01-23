package settingdust.more_enchantment_info

import net.fabricmc.fabric.api.resource.IdentifiableResourceReloadListener
import net.fabricmc.fabric.impl.resource.loader.ResourceManagerHelperImpl
import net.minecraft.resources.ResourceLocation
import net.minecraft.server.packs.PackType
import net.minecraft.server.packs.resources.PreparableReloadListener
import net.minecraft.server.packs.resources.ResourceManager
import net.minecraft.util.profiling.ProfilerFiller
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentCategory
import java.util.concurrent.Executor

object MoreEnchantmentInfo {
    const val MOD_ID = "more_enchantment_info"

    fun identifier(path: String) = ResourceLocation(MOD_ID, path)
}

object MoreEnchantmentInfoClient {
    object Sprites {
        val CATEGORY_ARMOR = MoreEnchantmentInfo.identifier("category_armor")
        val CATEGORY_ARMOR_HEAD = MoreEnchantmentInfo.identifier("category_armor_head")
        val CATEGORY_ARMOR_CHEST = MoreEnchantmentInfo.identifier("category_armor_chest")
        val CATEGORY_ARMOR_LEGS = MoreEnchantmentInfo.identifier("category_armor_legs")
        val CATEGORY_ARMOR_FEET = MoreEnchantmentInfo.identifier("category_armor_feet")
        val CATEGORY_WEAPON = MoreEnchantmentInfo.identifier("category_weapon")
        val CATEGORY_DIGGER = MoreEnchantmentInfo.identifier("category_digger")
        val CATEGORY_FISHING_ROD = MoreEnchantmentInfo.identifier("category_fishing_rod")
        val CATEGORY_TRIDENT = MoreEnchantmentInfo.identifier("category_trident")
        val CATEGORY_BREAKABLE = MoreEnchantmentInfo.identifier("category_breakable")
        val CATEGORY_BOW = MoreEnchantmentInfo.identifier("category_bow")
        val CATEGORY_WEARABLE = MoreEnchantmentInfo.identifier("category_wearable")
        val CATEGORY_CROSSBOW = MoreEnchantmentInfo.identifier("category_crossbow")
        val CATEGORY_VANISHABLE = MoreEnchantmentInfo.identifier("category_vanishable")

        val CURSE = MoreEnchantmentInfo.identifier("curse")
        val DISCOVERABLE = MoreEnchantmentInfo.identifier("discoverable")
        val ENCHANTABLE = MoreEnchantmentInfo.identifier("enchantable")
        val TRADEABLE = MoreEnchantmentInfo.identifier("tradable")
        val TREASURE = MoreEnchantmentInfo.identifier("treasure")

        val RARITY_COMMON = MoreEnchantmentInfo.identifier("rarity_common")
        val RARITY_UNCOMMON = MoreEnchantmentInfo.identifier("rarity_uncommon")
        val RARITY_RARE = MoreEnchantmentInfo.identifier("rarity_rare")
        val RARITY_VERY_RARE = MoreEnchantmentInfo.identifier("rarity_very_rare")

        val RARITY_TO_TEXTURE = mapOf(
            Enchantment.Rarity.COMMON to RARITY_COMMON,
            Enchantment.Rarity.UNCOMMON to RARITY_UNCOMMON,
            Enchantment.Rarity.RARE to RARITY_RARE,
            Enchantment.Rarity.VERY_RARE to RARITY_VERY_RARE
        )

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

    fun clientInit() {
    }

    fun onInitResourceReload() {
        @Suppress("UnstableApiUsage")
        ResourceManagerHelperImpl.get(PackType.CLIENT_RESOURCES)
            .registerReloadListener(
                object : IdentifiableResourceReloadListener {
                    private val ID = MoreEnchantmentInfo.identifier("sprite_uploader")

                    override fun getFabricId(): ResourceLocation {
                        return ID
                    }

                    override fun reload(
                        preparationBarrier: PreparableReloadListener.PreparationBarrier,
                        resourceManager: ResourceManager,
                        profilerFiller: ProfilerFiller,
                        profilerFiller2: ProfilerFiller,
                        executor: Executor,
                        executor2: Executor
                    ) = MoreEnchantmentInfoSpriteUploader.INSTANCE.reload(
                        preparationBarrier,
                        resourceManager,
                        profilerFiller,
                        profilerFiller2,
                        executor,
                        executor2
                    )
                })
    }

    fun viewEnchantment(enchantment: Enchantment) {
        throw UnsupportedOperationException("Mixin failed")
    }
}