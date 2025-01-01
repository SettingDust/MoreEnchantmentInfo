package settingdust.more_enchantment_info.mixin;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.Minecraft;
import net.minecraft.client.ResourceLoadStateTracker;
import net.minecraft.server.packs.PackResources;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import settingdust.more_enchantment_info.MoreEnchantmentInfoClient;

import java.util.List;

@Mixin(Minecraft.class)
public class MinecraftMixin {
    @WrapOperation(
        method = "<init>",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/ResourceLoadStateTracker;startReload(Lnet/minecraft/client/ResourceLoadStateTracker$ReloadReason;Ljava/util/List;)V"
        )
    )
    private void more_enchantment_info$initResourceReload(
        final ResourceLoadStateTracker instance,
        final ResourceLoadStateTracker.ReloadReason arg,
        final List<PackResources> list,
        final Operation<Void> original
    ) {
        MoreEnchantmentInfoClient.INSTANCE.onInitResourceReload();
        original.call(instance, arg, list);
    }
}
