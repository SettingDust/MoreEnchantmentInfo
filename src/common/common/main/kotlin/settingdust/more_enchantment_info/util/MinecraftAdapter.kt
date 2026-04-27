package settingdust.more_enchantment_info.util

interface MinecraftAdapter {
    companion object : MinecraftAdapter by ServiceLoaderUtil.findService()
}