package firenh.enchantmentpower.registry;

import com.mojang.serialization.Codec;
import eu.pb4.polymer.core.api.other.PolymerComponent;
import firenh.enchantmentpower.EnchantmentPower;
import net.minecraft.core.Registry;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.resources.Identifier;
import net.minecraft.util.ExtraCodecs;

public class EPDataComponents {
    public static final DataComponentType<Integer> ENCHANTMENT_POWER = new DataComponentType.Builder<Integer>()
        .persistent(Codec.INT)
        .ignoreSwapAnimation()
        .networkSynchronized(ByteBufCodecs.INT)
        .build();

    public static final DataComponentType<Float> ENCHANTMENT_POWER_RATIO = new DataComponentType.Builder<Float>()
        .persistent(Codec.FLOAT)
        .ignoreSwapAnimation()
        .networkSynchronized(ByteBufCodecs.FLOAT)
        .build();

    public static final DataComponentType<Boolean> HAS_OVERENCHANTMENTS = new DataComponentType.Builder<Boolean>()
        .persistent(Codec.BOOL)
        .ignoreSwapAnimation()
        .networkSynchronized(ByteBufCodecs.BOOL)
        .build();

    public static final Identifier ENCHANTMENT_POWER_ID = EnchantmentPower.id("enchantment_power");
    public static final Identifier ENCHANTMENT_POWER_RATIO_ID = EnchantmentPower.id("enchantment_power_ratio");
    public static final Identifier HAS_OVERENCHANTMENTS_ID = EnchantmentPower.id("has_overenchantments");

    public static void init() {
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ENCHANTMENT_POWER_ID, ENCHANTMENT_POWER);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, ENCHANTMENT_POWER_RATIO_ID, ENCHANTMENT_POWER_RATIO);
        Registry.register(BuiltInRegistries.DATA_COMPONENT_TYPE, HAS_OVERENCHANTMENTS_ID, HAS_OVERENCHANTMENTS);

        PolymerComponent.registerDataComponent(ENCHANTMENT_POWER);
        PolymerComponent.registerDataComponent(ENCHANTMENT_POWER_RATIO);
        PolymerComponent.registerDataComponent(HAS_OVERENCHANTMENTS);
    }
}
