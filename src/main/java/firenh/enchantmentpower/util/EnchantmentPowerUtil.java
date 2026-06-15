package firenh.enchantmentpower.util;

import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Optional;

public class EnchantmentPowerUtil {
    public static final int CHEAP = 5;
    public static final int INEXPENSIVE = 8;
    public static final int DEFAULT = 10;
    public static final int EXPENSIVE = 14;
    public static final int PREMIUM = 18;

    public static final int MAX_LEVELS = 40;

    public static int getEnchantmentLevels(ItemStack itemStack) {
        if (itemStack.is(Items.ENCHANTED_BOOK)) {
            // EnchantmentPower.LOGGER.info("is enchanted book!");
            return getEnchantmentLevels(itemStack.get(DataComponents.STORED_ENCHANTMENTS));
        }

        if (!itemStack.isEnchanted()) return -1;
        return getEnchantmentLevels(EnchantmentHelper.getEnchantmentsForCrafting(itemStack));
    }

    public static int getEnchantmentLevels(ItemStack itemStack, boolean abs) {
        if (itemStack.is(Items.ENCHANTED_BOOK)) {
            // EnchantmentPower.LOGGER.info("is enchanted book!");
            return getEnchantmentLevels(itemStack.get(DataComponents.STORED_ENCHANTMENTS), abs);
        }

        if (!itemStack.isEnchanted()) return -1;
        return getEnchantmentLevels(EnchantmentHelper.getEnchantmentsForCrafting(itemStack), abs);
    }

    public static int getEnchantmentLevels(ItemEnchantments component) {
        return getEnchantmentLevels(component, false);
    }

    public static int getEnchantmentLevels(ItemEnchantments component, boolean abs) {
        int power = 0;

        if (!abs) {
            for (Entry<Holder<Enchantment>> entry : component.entrySet()) {
                power += EnchantmentPowerUtil.getEnchantmentLevels(entry.getKey(), entry.getIntValue());
            }
        } else {
            for (Entry<Holder<Enchantment>> entry : component.entrySet()) {
                power += Math.abs(EnchantmentPowerUtil.getEnchantmentLevels(entry.getKey(), entry.getIntValue()));
            }
        }



        return power;
    }

    @SuppressWarnings("null")
    public static int getEnchantmentLevels(Holder<Enchantment> enchantment, int level) {
        int x = -(level - enchantment.value().getMaxLevel());
        int maxPower = DEFAULT;

//        Optional<Identifier> optId = getId(enchantment);
//
//        if (optId.isPresent()) {
//            var map = EnchantmentPower.getEnchantmentMaxPower();
//            if (map.containsKey(optId.get())) maxPower = map.get(optId.get());
//        }

        if (enchantment.is(EPEnchantmentTags.CHEAP)) maxPower = CHEAP;
        if (enchantment.is(EPEnchantmentTags.INEXPENSIVE)) maxPower = INEXPENSIVE;
        if (enchantment.is(EPEnchantmentTags.EXPENSIVE)) maxPower = EXPENSIVE;
        if (enchantment.is(EPEnchantmentTags.PREMIUM)) maxPower = PREMIUM;

        int levels = (int) Math.ceil((double) maxPower * Math.pow(2, -x));
        if (enchantment.is(EnchantmentTags.CURSE)) levels = -1 * levels;
        return levels;
    }

    public static int getMaxLevels() {
        return MAX_LEVELS;
    }

    public static Optional<Identifier> getId(Holder<Enchantment> enchantment) {
        Optional<ResourceKey<Enchantment>> optKey = enchantment.unwrapKey();
        if (optKey.isEmpty()) return Optional.empty();
        return Optional.of(optKey.get().identifier());
    }
}