package settingdust.more_enchantment_info.util

interface LoaderAdapter {
    companion object : LoaderAdapter by ServiceLoaderUtil.findService()

    val isClient: Boolean

    fun isModLoaded(modId: String): Boolean
}