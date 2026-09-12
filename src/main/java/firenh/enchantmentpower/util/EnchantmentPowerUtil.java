package firenh.enchantmentpower.util;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntMap.Entry;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Optional;
import java.util.Set;

public class EnchantmentPowerUtil {
    public static final int DEFAULT_COST = 10;

    public static final int DEFAULT_CAP = 40;
    public static final int MAX_CAP = 80;


    public static Optional<Identifier> getId(Holder<Enchantment> enchantment) {
        Optional<ResourceKey<Enchantment>> optKey = enchantment.unwrapKey();
        if (optKey.isEmpty()) return Optional.empty();
        return Optional.of(optKey.get().identifier());
    }

    public static int getCostFromTag(Holder<Enchantment> holder, int max) {
        for (int i = 0; i < max; i += 1) {
            if (holder.is(EPEnchantmentTags.getEnchantmentTag(i))) {
                return i;
            }
        }

        return DEFAULT_COST;
    }

    public static int getCapFromTag(ItemStack item) {
        for (int i = 0; i <= MAX_CAP; i += 1) {
            if (item.is(EPEnchantmentTags.getItemTag(i))) {
//                EnchantmentPower.log("Item " + item.getItem().toString() + "; Cap: " + i);
                return i;

            }
        }

        return DEFAULT_CAP;
    }
}