package settingdust.more_enchantment_info.v20

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureAtlasSprite
import net.minecraft.client.resources.TextureAtlasHolder
import settingdust.more_enchantment_info.MoreEnchantmentInfo
import settingdust.more_enchantment_info.util.Identifier

object MoreEnchantmentInfoSpriteUploader : TextureAtlasHolder(
    Minecraft.getInstance().textureManager,
    MoreEnchantmentInfo.TEXTURE_ATLAS_LOCATION,
    MoreEnchantmentInfo.SPRITE_TEXTURE_LOCATION,
) {
    public override fun getSprite(location: Identifier): TextureAtlasSprite? {
        return super.getSprite(location)
    }
}