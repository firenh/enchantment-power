package firenh.enchantmentpower.mixin;

import java.util.function.Consumer;

import net.minecraft.core.component.DataComponentGetter;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import firenh.enchantmentpower.util.TooltipUtil;

@Mixin(ItemEnchantments.class)
public class ItemEnchantmentsComponentMixin {
    @Inject(at = @At("RETURN"), method = "addToTooltip")
    public void appendTooltip(final Item.TooltipContext context, final Consumer<Component> consumer, final TooltipFlag flag, final DataComponentGetter components, CallbackInfo info) {
        TooltipUtil.appendTooltip((ItemEnchantments)(Object)this, context, consumer);
    }

}
