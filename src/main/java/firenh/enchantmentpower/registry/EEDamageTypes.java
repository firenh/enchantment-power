package firenh.enchantmentpower.registry;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;

import net.minecraft.world.damagesource.DamageType;

public class EEDamageTypes {
    public static final ResourceKey<DamageType> BRUTALUTY = of("brutality");

    private static ResourceKey<DamageType> of(String id) {
        return ResourceKey.create(Registries.DAMAGE_TYPE, Identifier.withDefaultNamespace(id));
    }
}
