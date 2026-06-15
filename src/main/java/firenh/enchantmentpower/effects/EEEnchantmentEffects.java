package firenh.enchantmentpower.effects;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.enchantment.effects.AllOf;

public class EEEnchantmentEffects {
    public static void init() {
        Registry.register(BuiltInRegistries.ENCHANTMENT_ENTITY_EFFECT_TYPE, "all_of", AllOf.EntityEffects.CODEC);
    }
}
