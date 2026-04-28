package settingdust.more_enchantment_info.v20.util

import net.minecraft.resources.ResourceLocation
import settingdust.more_enchantment_info.util.Identifier

fun Identifier.toNativeIdentifier(): ResourceLocation = ResourceLocation(namespace, path)

fun ResourceLocation.toCommonIdentifier(): Identifier = Identifier(namespace, path)