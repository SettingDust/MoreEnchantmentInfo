package settingdust.more_enchantment_info.mixin;

import net.minecraft.client.Minecraft;
import net.minecraft.client.main.GameConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import settingdust.more_enchantment_info.MoreEnchantmentInfoClient;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @Inject(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/ResourceLoadStateTracker;startReload(Lnet/minecraft/client/ResourceLoadStateTracker$ReloadReason;Ljava/util/List;)V"
        )
    )
    private void more_enchantment_info$initResourceReload(final GameConfig gameConfig, final CallbackInfo ci) {
        MoreEnchantmentInfoClient.INSTANCE.onInitResourceReload();
    }
}
