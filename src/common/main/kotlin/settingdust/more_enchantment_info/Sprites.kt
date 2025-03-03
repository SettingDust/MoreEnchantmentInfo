package settingdust.more_enchantment_info

import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.texture.TextureManager
import net.minecraft.client.resources.TextureAtlasHolder
import net.minecraft.resources.ResourceLocation

class MoreEnchantmentInfoSpriteUploader(textureManager: TextureManager) :
    TextureAtlasHolder(textureManager, LOCATION, MoreEnchantmentInfo.identifier("gui")) {
    companion object {
        val LOCATION = MoreEnchantmentInfo.identifier("textures/atlas/gui.png")
        val INSTANCE by lazy { MoreEnchantmentInfoSpriteUploader(Minecraft.getInstance().textureManager) }
    }

    public override fun getSprite(resourceLocation: ResourceLocation) = super.getSprite(resourceLocation)
}

