package firenh.enchantmentpower.polymer.itemcheck;

 import eu.pb4.polymer.core.api.item.PolymerItemUtils;
 import net.minecraft.world.item.ItemStack;
 import net.minecraft.world.item.enchantment.EnchantmentHelper;

 public class ItemChecks {
     public static void init() {
//          PolymerItemUtils.CONTEXT_ITEM_CHECK.register(itemStack -> {
//              return shouldGoThroughPolymer(itemStack);
//          });
     }

     public static boolean shouldGoThroughPolymer(ItemStack itemStack) {
         return hasEnchantments(itemStack);
     }

     public static boolean hasEnchantments(ItemStack itemStack) {
         return EnchantmentHelper.hasAnyEnchantments(itemStack);
     }
 }
