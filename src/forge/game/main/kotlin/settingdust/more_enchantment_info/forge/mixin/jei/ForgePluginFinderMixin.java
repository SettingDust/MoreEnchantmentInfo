package settingdust.more_enchantment_info.neoforge.mixin.jei;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import mezz.jei.neoforge.startup.ForgePluginFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.more_enchantment_info.util.MinecraftVersion;

import java.util.LinkedHashSet;

@Mixin(ForgePluginFinder.class)
public class ForgePluginFinderMixin {
    @ModifyExpressionValue(method = "getInstances", at = @At(value = "NEW", target = "java/util/LinkedHashSet"))
    private static LinkedHashSet<String> moreEnchantmentInfo$addPlugin(LinkedHashSet<String> original) {
        switch (MinecraftVersion.Companion.getCurrent()) {
            case V261 -> original.add("settingdust.more_enchantment_info.v26.jei.JEIMoreEnchantmentInfo");
            case V1211 -> original.add("settingdust.more_enchantment_info.v21.jei.JEIMoreEnchantmentInfo");
        }
        return original;
    }
}
