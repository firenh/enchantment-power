package firenh.enchantmentpower.util;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EnchantmentPower;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.ItemEnchantments;

public class GrindstoneUtil {
    public static boolean isUsingBook(Container slots) {
        return true;
//        return slots.getItem(1).is(Items.BOOK);
    }

    public static Pair<ItemStack, ItemStack> makeResult(ItemStack itemStack) {
        ItemEnchantments enchantments = itemStack.getEnchantments();
        ItemEnchantments.Mutable mut = new ItemEnchantments.Mutable(enchantments);

        int index = 0;
        final int last = enchantments.entrySet().size() - 2;

        Object2IntMap.Entry<Holder<Enchantment>> lastEnch = null;

        for (Object2IntMap.Entry<Holder<Enchantment>> e : enchantments.entrySet()) {
            EnchantmentPower.log(e.getKey().unwrapKey().get().toString());

            lastEnch = e;
            break;
        }

        mut.set(lastEnch.getKey(), 0);

        ItemStack result = itemStack.copy();
        result.set(DataComponents.ENCHANTMENTS, mut.toImmutable());

        ItemStack book = Items.BOOK.getDefaultInstance();
        book.enchant(lastEnch.getKey(), lastEnch.getIntValue());

        return new Pair<>(result, book);
    }


}
