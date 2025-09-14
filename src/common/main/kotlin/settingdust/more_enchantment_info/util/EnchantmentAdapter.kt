package settingdust.more_enchantment_info.util

import net.minecraft.client.Minecraft
import net.minecraft.client.resources.language.I18n
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.Registries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.enchantment.Enchantment
import net.minecraft.world.item.enchantment.EnchantmentInstance
import settingdust.more_enchantment_info.MoreEnchantmentInfoSprites
import kotlin.jvm.optionals.getOrNull

interface EnchantmentAdapter {
    companion object : EnchantmentAdapter by ServiceLoaderUtil.findService() {
        val DESCRIPTION_KEYS: Array<String> = arrayOf("desc", "description", "info")

        val registry: Registry<Enchantment>?
            get() = Minecraft.getInstance().level?.registryAccess()?.registryOrThrow(Registries.ENCHANTMENT)

        val Enchantment.key: ResourceLocation?
            get() = registry?.getKey(this)
        val Enchantment.resourceKey: ResourceKey<Enchantment>?
            get() = registry?.getResourceKey(this)?.getOrNull()
        val Enchantment.holder: Holder<Enchantment>?
            get() {
                return registry?.getHolder(resourceKey ?: return null)?.getOrNull()
            }
        val Enchantment.raritySprite: ResourceLocation
            get() = when {
                weight >= 10 -> MoreEnchantmentInfoSprites.RARITY_COMMON
                weight >= 5 -> MoreEnchantmentInfoSprites.RARITY_UNCOMMON
                weight >= 2 -> MoreEnchantmentInfoSprites.RARITY_RARE
                else -> MoreEnchantmentInfoSprites.RARITY_VERY_RARE
            }

        fun Enchantment.description(level: Int): Component? {
            val key = findDescriptionKey(level) ?: return null
            return Component.translatable(key)
        }

        fun Enchantment.findDescriptionKey(level: Int): String? {
            for (descriptionKey in DESCRIPTION_KEYS) {
                var key = "${descriptionPrefix}.${descriptionKey}"
                if (I18n.exists(key)) {
                    return key
                }
                key = "$key.$level"
                if (I18n.exists(key)) {
                    return key
                }
            }
            return null
        }
    }

    val Enchantment.descriptionPrefix: String
    val Enchantment.name: Component

    fun Enchantment.isCompatibleWith(other: Enchantment): Boolean
    val Enchantment.isLootable: Boolean
    val Enchantment.isTreasure: Boolean
    val Enchantment.isInTable: Boolean
    val Enchantment.isCurse: Boolean
    val Enchantment.isTradeable: Boolean
    val Enchantment.weight: Int

    val Enchantment.supportedCategories: List<ResourceLocation>

    fun EnchantmentInstance(enchantment: Holder<Enchantment>, level: Int): EnchantmentInstance
}