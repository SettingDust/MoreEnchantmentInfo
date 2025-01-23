package settingdust.more_enchantment_info.forge.mixin;

import mezz.jei.api.IModPlugin;
import mezz.jei.forge.startup.ForgePluginFinder;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import settingdust.more_enchantment_info.jei.JEIMoreEnchantmentInfo;

import java.util.List;

@Mixin(value = ForgePluginFinder.class, remap = false)
public class ForgePluginFinderMixin {
    /**
     * The annotation won't work since forge won't load the asm data from non-mod jar in jar
     */
    @Inject(method = "getInstances", at = @At("TAIL"))
    private static void more_enchantment_info$appendPlugin(
        final CallbackInfoReturnable<List<IModPlugin>> cir
    ) {
        cir.getReturnValue().add(new JEIMoreEnchantmentInfo(jeiHelpers));
    }
}
