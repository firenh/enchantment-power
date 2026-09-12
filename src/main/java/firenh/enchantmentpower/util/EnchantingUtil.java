package firenh.enchantmentpower.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Stream;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.registry.EPDataComponents;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.*;

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

    public static boolean hasOverenchantments(ItemStack stack) {
        ItemEnchantments enchantments = stack.get(EnchantmentHelper.getComponentType(stack));
        if (Objects.isNull(enchantments)) return false;

        for (var e : enchantments.entrySet()) {
            if (e.getIntValue() > e.getKey().value().getMaxLevel()) {
                return true;
            }
        }

        return false;
    }

    public static ItemStack overenchant(final RandomSource random, ItemStack itemStack, HolderSet<Enchantment> enchantments, int overEnchantments) {
        List<Holder<Enchantment>> enchList = new ArrayList<>();

        for (Holder<Enchantment> e : enchantments) {
            if (!e.is(EnchantmentTags.CURSE)) enchList.add(e);
        }

        List<Holder<Enchantment>> shuffledList = MiscUtil.shuffleList(enchList, random);

        ItemEnchantments.Mutable itemEnchantments = new ItemEnchantments.Mutable(itemStack.getEnchantments());
        final int intendedCap = EnchantmentPowerUtil.getCapFromTag(itemStack);
        final int targetPower = (int) (intendedCap + (random.nextFloat() * 0.25 * intendedCap) + (random.nextFloat() * 0.25 * intendedCap));

        addOverenchantments(itemEnchantments, shuffledList, itemStack, overEnchantments, random, targetPower, false);

        if (currentPower(itemEnchantments) < targetPower) {
            addOverenchantments(itemEnchantments, shuffledList, itemStack, 0, random, targetPower, true);
        }


        itemStack.set(EnchantmentHelper.getComponentType(itemStack), itemEnchantments.toImmutable());
        itemStack.set(EPDataComponents.HAS_OVERENCHANTMENTS, true);

        return itemStack;
    }

    private  static int currentPower(ItemEnchantments.Mutable entrySet) {
        int power = 0;

        for (var entry : entrySet.toImmutable().entrySet()) {
            Pair<Integer, Boolean> pair = EPower.getIndividualEnchantmentPower(entry.getKey(), entry.getIntValue());
            power += pair.getFirst();
        }

        return power;
    }

    private static void addOverenchantments(ItemEnchantments.Mutable itemEnchantments, List<Holder<Enchantment>> shuffledList, ItemStack itemStack, int overEnchantments, RandomSource random, int targetPower, boolean secondAttempt) {
        int currentOverEnchants = 0;

        outerloop:
        for (int i = 0; i < shuffledList.size(); i += 1) {
            Holder<Enchantment> enchHolder = shuffledList.get(i);
            Enchantment enchantment = enchHolder.value();

            if (
                    !enchantment.isSupportedItem(itemStack)
                    || itemStack.getEnchantments().keySet().contains(enchHolder)
            ) continue;

            for (Holder<Enchantment> e : itemEnchantments.toImmutable().keySet()) {
                if (!Enchantment.areCompatible(e, enchHolder)) continue outerloop;
            }

            int level;

            if (currentOverEnchants < overEnchantments && enchantment.getMaxLevel() > 1 && !enchHolder.is(EPEnchantmentTags.NEVER_EXCEED_MAX_LEVEL)) {
                level = enchantment.getMaxLevel() + 1;
                currentOverEnchants += 1;
            } else {

                if (secondAttempt) {
                    if (enchantment.getMaxLevel() > 1) {
                        level = random.nextInt(enchantment.getMaxLevel() - 1) + 1;
                    } else {
                        level = 1;
                    }
                } else {
                    level = random.nextInt(enchantment.getMaxLevel());
                }
            }

            itemEnchantments.set(enchHolder, level);
            int currentPower = currentPower(itemEnchantments);
            if (currentPower >= targetPower) break;
        }

        if (currentPower(itemEnchantments) < targetPower) {
            outerloop:
            for (int j = 0; j < 5; j+= 1) {
                for (Object2IntMap.Entry<Holder<Enchantment>> e : itemEnchantments.toImmutable().entrySet()) {
                    if (e.getIntValue() < e.getKey().value().getMaxLevel()) {
                        itemEnchantments.set(e.getKey(), e.getIntValue() + 1);
                        if (currentPower(itemEnchantments) >= targetPower) break outerloop;
                    }
                }
            }
        }
    }


}
