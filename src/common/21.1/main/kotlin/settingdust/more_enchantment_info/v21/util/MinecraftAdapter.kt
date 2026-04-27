package settingdust.more_enchantment_info.v21.util

import settingdust.more_enchantment_info.util.MinecraftAdapter
import settingdust.more_enchantment_info.util.MinecraftVersion

class MinecraftAdapter : MinecraftAdapter {
	init {
		MinecraftVersion.V1211.requireCurrent()
	}
}