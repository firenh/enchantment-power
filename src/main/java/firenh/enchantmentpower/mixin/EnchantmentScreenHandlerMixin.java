package firenh.enchantmentpower.mixin;

import java.util.List;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import firenh.enchantmentpower.gamerule.GameruleHack;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import net.minecraft.core.RegistryAccess;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.TagKey;
import net.minecraft.util.RandomSource;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.EnchantmentMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArg;
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

    @Shadow @Final
    private Container enchantSlots;


    @ModifyArg(method = "getEnchantmentList", at = @At(value = "INVOKE", target = "Lnet/minecraft/core/Registry;get(Lnet/minecraft/tags/TagKey;)Ljava/util/Optional;"), index = 0)
    private TagKey<Enchantment> addCurseEnchantments(TagKey<Enchantment> enchantmentKey) {
        if (enchantSlots.getItem(1).is(EPEnchantmentTags.ENCHANTMENT_CURSE_REAGENT)) {
            return EnchantmentTags.CURSE;
        }

        return enchantmentKey;
    }

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

    @WrapOperation(method = "quickMoveStack", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/item/ItemStack;is(Ljava/lang/Object;)Z"))
    public boolean quickMoveEnchantmentReagent(ItemStack instance, Object o, Operation<Boolean> original) {
        return original.call(instance, o) || instance.is(EPEnchantmentTags.ENCHANTMENT_CURSE_REAGENT);
    }
}
