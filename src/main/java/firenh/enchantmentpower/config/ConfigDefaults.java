package firenh.enchantmentpower.config;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EnchantmentPower;
import net.minecraft.resources.Identifier;
import org.jspecify.annotations.NonNull;

import java.util.HashMap;

public class ConfigDefaults {
    public static final int CHEAP = 5;
    public static final int INEXPENSIVE = 8;
    public static final int DEFAULT = 10;
    public static final int EXPENSIVE = 14;
    public static final int PREMIUM = 18;


    public static HashMap<String, Integer> getDefaultEnchantmentConfig() {
        HashMap<String, Integer> map = new HashMap<>();

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
                of("sweeping_edge", CHEAP),
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

//    public static HashMap<Identifier, Integer> getDefaultEnchantmentConfig() {
//        HashMap<Identifier, Integer> map = new HashMap<>();
//
////        put ?
//
//
//
//        return map;
//    }

    private static Identifier id(String id) {
        return EnchantmentPower.vid(id);
    }

    private static Pair<String, Integer> of(String id, Integer i) {
        return new Pair<String, Integer>(id, i);
    }

    private static void put(HashMap<String, Integer> map, @NonNull Pair<String, Integer>... pairs) {

        for (Pair<String, Integer> p : pairs) {
            map.put(p.getFirst(), p.getSecond());
        }

    }
}
