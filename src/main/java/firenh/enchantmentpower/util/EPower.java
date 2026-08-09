package firenh.enchantmentpower.util;

import com.mojang.datafixers.util.Pair;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class EPower {
    int power;
    int maxPower = 40;

    public EPower(int power, int maxPower) {
        this.power = power;
        this.maxPower = power;
    }

    public int getPower() { return this.power; }

    public int getMaxPower() { return this.maxPower; }


    public static EPower get(ItemStack itemStack, boolean abs) {
        if (!itemStack.isEnchanted()) {
            return new EPower(0, 0);
        }

        ItemEnchantments component = component = itemStack.getEnchantments();;
        int power = 0;

        if (itemStack.has(DataComponents.STORED_ENCHANTMENTS)) {
            component = itemStack.get(DataComponents.STORED_ENCHANTMENTS);
        }

        if (!abs) {
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : component.entrySet()) {
                power += EnchantmentPowerUtil.getEnchantmentLevels(entry.getKey(), entry.getIntValue());
            }
        } else {
            for (Object2IntMap.Entry<Holder<Enchantment>> entry : component.entrySet()) {
                power += Math.abs(EnchantmentPowerUtil.getEnchantmentLevels(entry.getKey(), entry.getIntValue()));
            }
        }

        return new EPower(power, 40);
    }

}
