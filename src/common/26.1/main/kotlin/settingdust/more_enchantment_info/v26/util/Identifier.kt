package settingdust.more_enchantment_info.util

import net.minecraft.resources.Identifier as MinecraftIdentifier

fun Identifier.toNativeIdentifier(): MinecraftIdentifier =
	MinecraftIdentifier.fromNamespaceAndPath(namespace, path)

fun MinecraftIdentifier.toCommonIdentifier(): Identifier = Identifier(namespace, path)