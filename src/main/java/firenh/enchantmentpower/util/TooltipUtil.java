package firenh.enchantmentpower.util;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;

public class TooltipUtil {
    public static void appendTooltip(ItemStack stack, Item.TooltipContext context, Consumer<Component> tooltip) {
        boolean cursesPresent = false;

		EPower power = EPower.get(stack);
		
        if (power.power() != 0 || power.cursesPresent()) {
            tooltip.accept(
                Component.translatable("enchantmentpower.enchantment_levels", power.power(), power.maxPower())
                .withStyle(getTooltipColor(power))
            );
        }
    }

    private static ChatFormatting getTooltipColor(EPower power) {
        if (power.power() == power.maxPower()) return ChatFormatting.DARK_PURPLE;
        if (power.power() > power.maxPower()) return ChatFormatting.LIGHT_PURPLE;
        if (power.power() <= 0) return ChatFormatting.DARK_RED;
        if (power.power() > power.maxPower() / 2) return ChatFormatting.BLUE;
        return ChatFormatting.GRAY;
    }
}