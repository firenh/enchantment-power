package firenh.enchantmentpower.util;

import java.util.ArrayList;
import java.util.List;
import net.minecraft.world.item.enchantment.EnchantmentInstance;

public class EnchantingUtil {
    public static List<EnchantmentInstance> generateEnchantments(List<EnchantmentInstance> list, int level) {
        int power = 0;
        List<EnchantmentInstance> newList = new ArrayList<>();

        for (EnchantmentInstance entry : list) {
            int thisEntryPower = EnchantmentPowerUtil.getEnchantmentLevels(entry.enchantment(), entry.level());

            if (power + thisEntryPower <= EnchantmentPowerUtil.getMaxLevels()) {
                power += thisEntryPower;
                newList.add(entry);
            } else {
                // EleanorsEnchantments.log("Overpowered enchantment!");
                break;
            }
        }

        if (power < level) {
            for (EnchantmentInstance entry : list) {
                if (entry.level() >= entry.enchantment().value().getMaxLevel()) continue;
                EnchantmentInstance newEntry = new EnchantmentInstance(entry.enchantment(), entry.level() + 1);

                if (power + EnchantmentPowerUtil.getEnchantmentLevels(entry.enchantment(), entry.level()) <= EnchantmentPowerUtil.getMaxLevels()) {
                    // EleanorsEnchantments.log("Powering up enchantment!");
                    entry = newEntry;
                }
            }
        }

        return newList;
    } 
}
