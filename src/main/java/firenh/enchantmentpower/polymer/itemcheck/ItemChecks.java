package firenh.enchantmentpower.polymer.itemcheck;

 import eu.pb4.polymer.core.api.item.PolymerItemUtils;
 import firenh.enchantmentpower.EnchantmentPower;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.enchantment.EnchantmentHelper;
 import net.fabricmc.fabric.impl.networking.context.PacketContextImpl;

 public class ItemChecks {
     public static void init() {
          PolymerItemUtils.CONTEXT_ITEM_CHECK.register((instance, context) -> {
                if (instance instanceof ItemStack) return shouldGoThroughPolymer((ItemStack)instance);
                return false;
          });
     }

     public static boolean shouldGoThroughPolymer(ItemStack itemStack) {
         return hasEnchantments(itemStack);
     }

     public static boolean hasEnchantments(ItemStack itemStack) {
         return EnchantmentHelper.hasAnyEnchantments(itemStack);
     }
 }
