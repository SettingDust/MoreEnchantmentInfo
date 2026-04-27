package settingdust.more_enchantment_info.v21

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.TextureAtlasHolder
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Identifier
import settingdust.more_enchantment_info.util.toNativeIdentifier

object MoreEnchantmentInfoSpriteUploader : TextureAtlasHolder(
    Minecraft.getInstance().textureManager,
    MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION.toNativeIdentifier(),
    MoreEnchantmentInfo.SPRITE_TEXTURE_LOCATION.toNativeIdentifier(),
) {
    fun getSprite(location: Identifier): TextureAtlasSprite? {
        return super.getSprite(location.toNativeIdentifier())
    }
}