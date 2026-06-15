package firenh.enchantmentpower.polymer.itemcheck;

import eu.pb4.polymer.core.api.item.PolymerItemUtils;
import net.minecraft.core.component.DataComponents;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.ItemLore;
import net.minecraft.world.item.enchantment.EnchantmentHelper;

public class ItemModification {
 public static void init() {
     PolymerItemUtils.ITEM_MODIFICATION_EVENT.register(
         (original, client, player) -> {
             if (hasEnchantments(original)) {
                 client.setCount(3);
                 client.update(DataComponents.LORE, ItemLore.EMPTY.withLineAdded(Component.nullToEmpty("h")), null);
             }
             return Items.DIAMOND.getDefaultInstance();
         }
     );
 }

 public static boolean hasEnchantments(ItemStack itemStack) {
     return EnchantmentHelper.hasAnyEnchantments(itemStack);
 }
}
