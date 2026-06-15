package firenh.enchantmentpower.util;

import com.google.common.collect.Maps;
import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EleanorsEnchantments;
import net.minecraft.core.Holder;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.enchantment.Enchantment;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;

public class EnchantmentsConfig {
    public static final int CHEAP = 9;
    public static final int INEXPENSIVE = 14;
    public static final int DEFAULT = 18;
    public static final int EXPENSIVE = 22;
    public static final int PREMIUM = 28;


    public static HashMap<Identifier, Integer> getDefaultConfig() {
        HashMap<Identifier, Integer> map = new HashMap<>();

        put(map,

            //  All Equipment
                of("mending", PREMIUM),
                of("unbreaking", INEXPENSIVE),
                of("vanishing_curse", DEFAULT),

            //  Armor
                of("aqua_affinity", CHEAP),
                of("blast_protection", DEFAULT),
                of("binding_curse", PREMIUM),
                of("depth_strider", DEFAULT),
                of("feather_falling", EXPENSIVE),
                of("fire_protection", DEFAULT),
                of("frost_walker", CHEAP),
                of("projectile_protection", DEFAULT),
                of("protection", EXPENSIVE),
                of("respiration", INEXPENSIVE),
                of("soul_speed", DEFAULT),
                of("thorns", INEXPENSIVE),
                of("swift_sneak", DEFAULT),

            //  Melee
                of("bane_of_arthropods", INEXPENSIVE),
                of("breach", DEFAULT),
                of("density", EXPENSIVE),
                of("fire_aspect", INEXPENSIVE),
                of("looting", EXPENSIVE),
                of("lunge", DEFAULT),
                of("impaling", INEXPENSIVE),
                of("knockback", INEXPENSIVE),
                of("sharpness", EXPENSIVE),
                of("smite", DEFAULT),
                of("sweeping_edge", DEFAULT),
                of("wind_burst", DEFAULT),

            //  Ranged
                of("channeling", EXPENSIVE),
                of("flame", DEFAULT),
                of("infinity", EXPENSIVE),
                of("loyalty", DEFAULT),
                of("riptide", DEFAULT),
                of("multishot", INEXPENSIVE),
                of("piercing", INEXPENSIVE),
                of("power", PREMIUM),
                of("punch", DEFAULT),
                of("quick_charge", INEXPENSIVE),

            //  Tools
                of("efficiency", EXPENSIVE),
                of("fortune", DEFAULT),
                of("luck_of_the_sea", INEXPENSIVE),
                of("lure", INEXPENSIVE),
                of("silk_touch", DEFAULT)
        );

        return map;
    }

    private static Identifier id(String id) {
        return EleanorsEnchantments.vid(id);
    }

    private static Pair<Identifier, Integer> of(String id, Integer i) {
        return new Pair<>(id(id), i);
    }

    private static void put(HashMap<Identifier, Integer> map, @NonNull Pair<Identifier, Integer>... pairs) {

        for (Pair<Identifier, Integer> p : pairs) {
            map.put(p.getFirst(), p.getSecond());
        }

    }
}
