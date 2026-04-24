package settingdust.more_enchantment_info.util

expect class Identifier {
    fun getNamespace(): String
    fun getPath(): String

    override fun toString(): String
}