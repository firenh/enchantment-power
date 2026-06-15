package firenh.enchantmentpower.util;

import java.util.function.Consumer;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;

public class TooltipUtil {
    public static void appendTooltip(ItemEnchantments component, Item.TooltipContext context, Consumer<Component> tooltip) {
        int maxPower = EnchantmentPowerUtil.getMaxLevels();
        int power = 0;
        boolean cursesPresent = false;

		for (Entry<Holder<Enchantment>> entry : component.entrySet()) {
            int newPower = EnchantmentPowerUtil.getEnchantmentLevels(entry.getKey(), entry.getIntValue());
            power += newPower;
            if (newPower < 0) cursesPresent = true;
        }
		
        if (power != 0 || cursesPresent) {
            tooltip.accept(
                Component.translatable("enchantmentpower.enchantment_levels", power, maxPower)
                .withStyle(getTooltipColor(power, maxPower, cursesPresent))
            );
        }
    }

    private static ChatFormatting getTooltipColor(int power, int maxPower, boolean cursesPresent) {
        if (power == maxPower) return ChatFormatting.DARK_PURPLE;
        if (power > maxPower) return ChatFormatting.LIGHT_PURPLE;
        if (power < 0) return ChatFormatting.DARK_RED;
        if (power > maxPower / 2) return ChatFormatting.BLUE;
        return ChatFormatting.GRAY;
    }
}