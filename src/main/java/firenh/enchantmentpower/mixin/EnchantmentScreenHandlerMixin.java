package firenh.enchantmentpower.mixin;

import java.util.List;

import firenh.enchantmentpower.gamerule.GameruleHack;
import net.minecraft.core.RegistryAccess;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import firenh.enchantmentpower.util.EnchantingUtil;

@Mixin(EnchantmentMenu.class)
public class EnchantmentScreenHandlerMixin {
    @Shadow
    @Final
    private DataSlot enchantmentSeed;

    @Shadow
    @Final
    private RandomSource random;

    @Inject(at = @At("RETURN"), method = "getEnchantmentList", cancellable = true)
    private void generateEnchantments(RegistryAccess registryManager, ItemStack stack, int slot, int level, CallbackInfoReturnable<List<EnchantmentInstance>> info) {
        info.setReturnValue(EnchantingUtil.generateEnchantments(info.getReturnValue(), level, stack));
    }

    @Inject(method = "slotsChanged", at = @At("HEAD"))
    public void slotsChanged(final Container container, CallbackInfo info) {
        if (container.getItem(0).isEmpty() && GameruleHack.reroll_enchantments.getValue()) {
            enchantmentSeed.set(random.nextInt());
        }
    }
}
