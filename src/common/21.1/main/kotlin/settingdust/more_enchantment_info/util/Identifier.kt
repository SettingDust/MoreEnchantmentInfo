package settingdust.more_enchantment_info.util

import net.minecraft.resources.ResourceLocation

fun Identifier.toNativeIdentifier(): ResourceLocation = ResourceLocation.fromNamespaceAndPath(namespace, path)

fun ResourceLocation.toCommonIdentifier(): Identifier = Identifier(namespace, path)