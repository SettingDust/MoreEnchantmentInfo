package settingdust.more_enchantment_info.forge.mixin.jei;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mezz.jei.forge.startup.ForgePluginFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.more_enchantment_info.util.MinecraftVersion;

import java.util.LinkedHashSet;

@Mixin(ForgePluginFinder.class)
public class ForgePluginFinderMixin {
    @ModifyExpressionValue(method = "getInstances", at = @At(value = "NEW", target = "java/util/LinkedHashSet"))
    private static LinkedHashSet<String> moreEnchantmentInfo$addPlugin(LinkedHashSet<String> original) {
        if (MinecraftVersion.Companion.getCurrent() == MinecraftVersion.V1201) {
            original.add("settingdust.more_enchantment_info.v20.jei.JEIMoreEnchantmentInfo");
        }
        return original;
    }
}
