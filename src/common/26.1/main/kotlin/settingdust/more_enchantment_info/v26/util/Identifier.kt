package settingdust.more_enchantment_info.v26.util

import settingdust.more_enchantment_info.util.Identifier
import net.minecraft.resources.Identifier as MinecraftIdentifier

fun Identifier.toNativeIdentifier(): MinecraftIdentifier =
	MinecraftIdentifier.fromNamespaceAndPath(namespace, path)

fun MinecraftIdentifier.toCommonIdentifier(): Identifier = Identifier(namespace, path)