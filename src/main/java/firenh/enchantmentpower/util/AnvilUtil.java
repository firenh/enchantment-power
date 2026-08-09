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

        ItemStack inputItem = ((ForgingScreenHandlerAccessor)screenHandler).getInputSlots().getItem(0);
        ItemStack outputItem = ((ForgingScreenHandlerAccessor)screenHandler).getResultSlots().getItem(0);

        EPower power = EPower.get(outputItem);
        // EleanorsEnchantments.LOGGER.info("enchantingLevels: " + enchantingLevels);
        if (power.power() > 0 && power.power() > power.maxPower()) {
            ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, ItemStack.EMPTY);
            ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(-1);
            return;
        }

        outputItem.update(DataComponents.REPAIR_COST, 0, i -> 0);
        ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, outputItem);
        ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(getCost(inputItem, outputItem, power.power()));
    }

    private static int getCost(ItemStack inputItem, ItemStack outputItem, int outputPower) {
        if (!EPower.hasEnchantments(outputItem)) {
            return 1;
        }

        int inputPower = EPower.get(inputItem).power();

        if (inputPower == outputPower) {
            return 1;
        }

        return outputPower > 0 ? outputPower : 1;
    }
}
