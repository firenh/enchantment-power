package firenh.enchantmentpower.util;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.config.ConfigGetter;
import firenh.enchantmentpower.registry.EPEnchantmentTags;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

import java.util.Objects;
import java.util.Optional;

public class EPower {
    int power;
    int maxPower;
    boolean cursesPresent;

    public EPower(int power, int maxPower, boolean cursesPresent) {
        this.power = power;
        this.maxPower = maxPower;
        this.cursesPresent = cursesPresent;
    }

    public int power() { return this.power; }

    public int maxPower() { return this.maxPower; }

    public boolean cursesPresent() { return this.cursesPresent; }

    public static boolean hasEnchantments(ItemStack stack) {
        return (stack.isEnchanted() || stack.has(DataComponents.STORED_ENCHANTMENTS));
    }

    public static EPower get(ItemStack itemStack) {
        if (!hasEnchantments(itemStack)) {
            return new EPower(0, 0, false);
        }

        ItemEnchantments component = itemStack.getEnchantments();
        int power = 0;
        int maxPower = 40;
        boolean cursesPresent = false;

        if (itemStack.has(DataComponents.STORED_ENCHANTMENTS) || itemStack.is(Items.ENCHANTED_BOOK)) {
            component = itemStack.get(DataComponents.STORED_ENCHANTMENTS);
        }

        if (Objects.isNull(component)) {
            return new EPower(0, 0, false);
        }

        for (Object2IntMap.Entry<Holder<Enchantment>> entry : component.entrySet()) {
            Pair<Integer, Boolean> pair = getIndividualEnchantmentPower(entry.getKey(), entry.getIntValue());
            power += pair.getFirst();
            if (!cursesPresent) cursesPresent = pair.getSecond();
        }

        maxPower = EnchantmentPowerUtil.getCapFromTag(itemStack);

        return new EPower(power, maxPower, cursesPresent);
    }

    public static Pair<Integer, Boolean> getIndividualEnchantmentPower(Holder<Enchantment> enchantment, int level) {
        int maxLvl = enchantment.value().getMaxLevel();
        int maxPower = EnchantmentPowerUtil.DEFAULT_COST;
        boolean cursesPresent = false;


        Optional<ResourceKey<Enchantment>> wrapper = enchantment.unwrapKey();

        if (wrapper.isEmpty()) {
            return new Pair<>(0, false);
        }

        maxPower = EnchantmentPowerUtil.getCostFromTag(enchantment, 50);

        double levels = Math.ceil(level * ((double)maxPower / ((double) maxLvl)));

        if (enchantment.is(EnchantmentTags.CURSE)) {
            levels = -1 * levels;
            cursesPresent = true;
        }
        return new Pair<>((int) levels, cursesPresent);
    }


}
