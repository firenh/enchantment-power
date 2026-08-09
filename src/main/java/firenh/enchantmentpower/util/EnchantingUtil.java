package firenh.enchantmentpower.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

import firenh.enchantmentpower.EnchantmentPower;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.EnchantmentInstance;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EnchantingUtil {
    public static List<EnchantmentInstance> generateEnchantments(List<EnchantmentInstance> list, int level, ItemStack itemStack) {
        int power = 0;

        int maxPower = EnchantmentPowerUtil.getCapFromTag(itemStack);

//        EnchantmentPower.log("MaxPower: " + maxPower + "; Item: " + itemStack.getItem().toString());

        List<EnchantmentInstance> newList = new ArrayList<>();

        for (EnchantmentInstance entry : list) {
            int thisEntryPower = EPower.getIndividualEnchantmentPower(entry.enchantment(), entry.level()).getFirst();

            if (power + thisEntryPower <= maxPower) {
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

                if (power + EPower.getIndividualEnchantmentPower(entry.enchantment(), entry.level()).getFirst() <= maxPower) {
                    // EleanorsEnchantments.log("Powering up enchantment!");
                    entry = newEntry;
                }
            }
        }

        return newList;
    }

    public static ItemStack overenchant(final RandomSource random, ItemStack itemStack, final Stream<Holder<Enchantment>> source) {
        List<EnchantmentInstance> enchantments1 = EnchantmentHelper.selectEnchantment(random, itemStack, Integer.MAX_VALUE, source);

        int overenchantIndex = random.nextInt(enchantments1.size());

        for (int i = 0; i < enchantments1.size(); i += 1) {
            EnchantmentInstance enchant = enchantments1.get(i);
            int levels = enchant.level();

            if (i == overenchantIndex) {
                levels = enchant.enchantment().value().getMaxLevel() + 1;
            }

            itemStack.enchant(enchant.enchantment(), levels);
        }

        return itemStack;
    }


}
