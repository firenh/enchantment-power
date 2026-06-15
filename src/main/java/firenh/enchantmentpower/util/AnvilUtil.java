package firenh.enchantmentpower.util;

import firenh.enchantmentpower.mixin.AnvilScreenHandlerAccessor;
import firenh.enchantmentpower.mixin.ForgingScreenHandlerAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;

public class AnvilUtil {
    public static void updateAnvilResult (AnvilMenu screenHandler) {
        // EleanorsEnchantments.log("repairItemUsage: " + ((AnvilScreenHandlerAccessor)screenHandler).getRepairItemUsage());
        ((AnvilScreenHandlerAccessor)screenHandler).setRepairItemCountCost(0);

        ItemStack outputItem = ((ForgingScreenHandlerAccessor)screenHandler).getResultSlots().getItem(0);
        int enchantingLevels = EnchantmentPowerUtil.getEnchantmentLevels(outputItem);
        // EleanorsEnchantments.LOGGER.info("enchantingLevels: " + enchantingLevels);
        if (enchantingLevels > 0 && enchantingLevels > EnchantmentPowerUtil.getMaxLevels()) {
            ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, ItemStack.EMPTY);
            ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(-1);
            return;
        }

        outputItem.update(DataComponents.REPAIR_COST, 0, i -> 0);
        ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, outputItem);
        ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(enchantingLevels > 0 ? enchantingLevels : 1);
    }
}
