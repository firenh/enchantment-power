package firenh.enchantmentpower.mixin;

import java.util.List;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import firenh.enchantmentpower.util.EnchantingUtil;

@Mixin(EnchantmentMenu.class)
public class EnchantmentScreenHandlerMixin {
    @Inject(at = @At("RETURN"), method = "getEnchantmentList", cancellable = true)
    private void generateEnchantments(RegistryAccess registryManager, ItemStack stack, int slot, int level, CallbackInfoReturnable<List<EnchantmentInstance>> info) {
        info.setReturnValue(EnchantingUtil.generateEnchantments(info.getReturnValue(), level, stack));
    }
}
