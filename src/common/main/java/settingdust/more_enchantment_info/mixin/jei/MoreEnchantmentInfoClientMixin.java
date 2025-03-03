package settingdust.more_enchantment_info.mixin.jei;

import net.minecraft.world.item.enchantment.Enchantment;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import settingdust.more_enchantment_info.MoreEnchantmentInfoClient;
import settingdust.more_enchantment_info.jei.EnchantmentIngredientHelper;

@Mixin(value = MoreEnchantmentInfoClient.class, remap = false)
public class MoreEnchantmentInfoClientMixin {
    /**
     * @author SettingDust
     * @reason Implement
     */
    @Overwrite
    public void viewEnchantment(Enchantment enchantment) {
        EnchantmentIngredientHelper.INSTANCE.viewEnchantment(enchantment);
    }
}
