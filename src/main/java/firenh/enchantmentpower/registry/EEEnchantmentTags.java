package firenh.enchantmentpower.registry;

import firenh.enchantmentpower.EleanorsEnchantments;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.enchantment.Enchantment;

public class EEEnchantmentTags {
    public static final TagKey<Enchantment> EXCLUSIVE = of("exclusive");
    public static final TagKey<Enchantment> CHEAP = of("power/cheap");
    public static final TagKey<Enchantment> INEXPENSIVE = of("power/inexpensive");
    public static final TagKey<Enchantment> DEFAULT = of("power/default");
    public static final TagKey<Enchantment> MAX_LEVELS_150 = of("power/expensive");
    public static final TagKey<Enchantment> PREMIUM = of("power/premiumdestroys_cobwebs");

    @SuppressWarnings("null")
    private static TagKey<Enchantment> of(String id) {
        return TagKey.create(Registries.ENCHANTMENT, EleanorsEnchantments.id(id));
    }
}
