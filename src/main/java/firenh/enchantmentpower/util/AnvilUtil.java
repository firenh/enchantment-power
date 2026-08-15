package firenh.enchantmentpower.util;

import firenh.enchantmentpower.gamerule.EPGamerules;
import firenh.enchantmentpower.mixin.AnvilScreenHandlerAccessor;
import firenh.enchantmentpower.mixin.ForgingScreenHandlerAccessor;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class AnvilUtil {
    public static enum AnvilRepairType {
        ENCHANT,
        REPAIR,
        RENAME
    }

    public static AnvilRepairType anvilRepairType(ItemStack input, ItemStack addition, ItemStack output) {
        if (addition.isEmpty()) return AnvilRepairType.RENAME;

        if (!addition.getOrDefault(EnchantmentHelper.getComponentType(addition), ItemEnchantments.EMPTY).isEmpty()
            || EPower.get(input).power() != EPower.get(output).power()
        ) {
            return AnvilRepairType.ENCHANT;
        }

        if (addition.getDamageValue() != output.getDamageValue()) {
            return AnvilRepairType.REPAIR;
        }

        return AnvilRepairType.RENAME;
    }

    public static void updateAnvilResult (AnvilMenu screenHandler) {
        ((AnvilScreenHandlerAccessor)screenHandler).setRepairItemCountCost(0);

        ItemStack inputItem = ((ForgingScreenHandlerAccessor)screenHandler).getInputSlots().getItem(0);
        ItemStack additionItem = ((ForgingScreenHandlerAccessor)screenHandler).getInputSlots().getItem(1);
        ItemStack outputItem = ((ForgingScreenHandlerAccessor)screenHandler).getResultSlots().getItem(0);

        EPower power = EPower.get(outputItem);
        AnvilRepairType repairType = anvilRepairType(inputItem, additionItem, outputItem);

        if (    repairType.equals(AnvilRepairType.ENCHANT)
                && !((ForgingScreenHandlerAccessor)(Object)screenHandler).getPlayer().hasInfiniteMaterials()
                && ((
                    power.power() > 0
                    && power.power() > power.maxPower()
                ) || (
                    EnchantingUtil.hasOverenchantments(inputItem)
                ))
        ) {
            ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, ItemStack.EMPTY);
            ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(-1);
            return;
        }

        outputItem.update(DataComponents.REPAIR_COST, 0, i -> 0);
        ((ForgingScreenHandlerAccessor) screenHandler).getResultSlots().setItem(0, outputItem);
        ((AnvilScreenHandlerAccessor) screenHandler).getCost().set(getCost(repairType, inputItem, outputItem, power.power(), screenHandler));
    }

    private static int getCost(AnvilRepairType repairType, ItemStack inputItem, ItemStack outputItem, int outputPower, AnvilMenu menu) {
        if (repairType.equals(AnvilRepairType.RENAME)) return 1;

        if (repairType.equals(AnvilRepairType.REPAIR)) {
            int maxPower = EPower.get(outputItem).maxPower();
            if (outputPower > maxPower) return outputPower - maxPower;
            return outputPower;
        }

        int anvilCostCap = Integer.MAX_VALUE;
        double anvilCostMultiplier = 0.5;

        if (((ForgingScreenHandlerAccessor) (Object) menu).getPlayer().level() instanceof ServerLevel serverLevel) {
            anvilCostCap = serverLevel.getGameRules().get(EPGamerules.ANVIL_COST_CAP);
            anvilCostMultiplier = serverLevel.getGameRules().get(EPGamerules.ANVIL_COST_PERCENTAGE_MULTIPLIER) / 100.0;
        }

        int cost = (int)(outputPower * anvilCostMultiplier);
        return Math.clamp(cost, 1, anvilCostCap);
    }
}
