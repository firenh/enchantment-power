package firenh.enchantmentpower.mixin;

import firenh.enchantmentpower.util.EPower;
import firenh.enchantmentpower.util.TooltipUtil;
import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.component.TooltipProvider;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import firenh.enchantmentpower.util.EnchantmentPowerUtil;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;

import java.util.function.Consumer;

@Mixin(ItemStack.class)
public class ItemStackMixin {
    @Inject(at = @At("HEAD"), method = "getRarity", cancellable = true)
    public void getRarity(CallbackInfoReturnable<Rarity> info) {
        if (((ItemStack)(Object)this).isEnchanted()) {
            Rarity rarity = ((ItemStack)(Object)this).getOrDefault(DataComponents.RARITY, Rarity.COMMON);
            EPower power = EPower.get(((ItemStack)(Object)this));
            int rarityBoost = 0;

            if (power.power() > power.maxPower()) rarityBoost = 3;
            else if (power.power() == power.maxPower()) rarityBoost = 2;
            else if (power.power() > power.maxPower() / 2) rarityBoost = 1;

            int newRarity = rarity.ordinal() + rarityBoost;
            if (newRarity > 3) newRarity = 3;
            info.setReturnValue(Rarity.values()[newRarity]);
        }
    }

    @Inject(at = @At("RETURN"), method = "addToTooltip")
    public <T extends TooltipProvider> void addToTooltip(final DataComponentType<T> type, final Item.TooltipContext context, final TooltipDisplay display, final Consumer<Component> consumer, final TooltipFlag flag, CallbackInfo info) {
        if (type.equals(DataComponents.ENCHANTMENTS)) {
            TooltipUtil.appendTooltip( ((ItemStack)(Object)this), context, consumer);
        }
    }
}
