package firenh.enchantmentpower.mixin;

import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(targets = "net/minecraft/world/inventory/EnchantmentMenu$3")
public class EnchantmentMenuAnonymousLapisSlotMixin {
    @Inject(method = "Lnet/minecraft/world/inventory/EnchantmentMenu$3;mayPlace(Lnet/minecraft/world/item/ItemStack;)Z", at = @At("RETURN"), cancellable = true)
    private static void testMixin(ItemStack itemStack, CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(
            cir.getReturnValueZ()
            || itemStack.is(EPEnchantmentTags.ENCHANTMENT_CURSE_REAGENT)
        );
    }
}
