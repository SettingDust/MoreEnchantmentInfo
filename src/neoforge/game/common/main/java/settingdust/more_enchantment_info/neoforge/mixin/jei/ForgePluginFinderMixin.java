package settingdust.more_enchantment_info.neoforge.mixin.jei;

import com.llamalad7.mixinextras.sugar.Local;
import mezz.jei.neoforge.startup.ForgePluginFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import settingdust.more_enchantment_info.util.MinecraftVersion;

import java.util.List;
import java.util.Set;

@Mixin(ForgePluginFinder.class)
public class ForgePluginFinderMixin {
    @Inject(
            method = "getInstances",
            at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private static void moreEnchantmentInfo$addPlugin(
            Class<?> annotationClass,
            Class<?> instanceClass,
            CallbackInfoReturnable<List<?>> cir,
            @Local(name = "pluginClassNames") Set<String> pluginClassNames) {
        switch (MinecraftVersion.Companion.getCurrent()) {
            case V261,V262 -> pluginClassNames.add("settingdust.more_enchantment_info.v26.jei.JEIMoreEnchantmentInfo");
            case V1211 -> pluginClassNames.add("settingdust.more_enchantment_info.v21.jei.JEIMoreEnchantmentInfo");
        }
    }
}
