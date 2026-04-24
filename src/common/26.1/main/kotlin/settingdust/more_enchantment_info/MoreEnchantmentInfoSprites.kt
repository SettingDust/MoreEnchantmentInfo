package settingdust.more_enchantment_info

import net.minecraft.client.renderer.texture.TextureAtlasSprite
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.v26.MoreEnchantmentInfoAtlasManager


@Suppress("ACTUAL_WITHOUT_EXPECT")
actual fun MoreEnchantmentInfoSprites.getSprite(id: Identifier): TextureAtlasSprite? {
    return MoreEnchantmentInfoAtlasManager.getAtlas().getSprite(id)
}