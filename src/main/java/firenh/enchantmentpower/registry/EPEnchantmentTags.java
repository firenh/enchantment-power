package firenh.enchantmentpower.registry;

import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.EnchantmentPower;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class EPEnchantmentTags {
    public static final TagKey<Enchantment> CHEAP = of("power/cheap");
    public static final TagKey<Enchantment> INEXPENSIVE = of("power/inexpensive");
    public static final TagKey<Enchantment> DEFAULT = of("power/default");
    public static final TagKey<Enchantment> EXPENSIVE = of("power/expensive");
    public static final TagKey<Enchantment> PREMIUM = of("power/premium");

    @SuppressWarnings("null")
    private static TagKey<Enchantment> of(String id) {
        return TagKey.create(Registries.ENCHANTMENT, EnchantmentPower.id(id));
    }
}
