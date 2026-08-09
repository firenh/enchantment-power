package firenh.enchantmentpower.config;

import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.util.EnchantmentPowerUtil;
import net.minecraft.resources.Identifier;

import java.util.HashMap;
import java.util.Optional;

public class ConfigGetter {
    public static Optional<Integer> getMaxPower(Identifier id) {
        HashMap<String, Integer> enchantmentPowers = conf().enchantment_powers;
        String strid = id.toString();

        if (!enchantmentPowers.containsKey(strid)) {
            return Optional.empty();
        }

        return Optional.of(conf().enchantment_powers.get(strid));
    }

    private static EPConfig conf() {
        return EnchantmentPower.getConfig();
    }


}
