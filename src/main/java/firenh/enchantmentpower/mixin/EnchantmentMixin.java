package firenh.enchantmentpower.mixin;

import net.fabricmc.fabric.api.client.message.v1.ClientSendMessageEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.ComponentUtils;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.network.chat.Style;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.core.Holder;
import net.minecraft.world.item.enchantment.Enchantment;

@Mixin(Enchantment.class)
public class EnchantmentMixin {
    @Inject(at = @At("RETURN"), method = "getFullname", cancellable = true)
    private static void getFullname(Holder<Enchantment> enchantment, int level, CallbackInfoReturnable<Component> info) {
        if (level > enchantment.value().getMaxLevel()) {
            MutableComponent component = info.getReturnValue().copy();
            component.setStyle(component.getStyle().withColor(0xac85b2));
            info.setReturnValue(component);
        }
    }
}
