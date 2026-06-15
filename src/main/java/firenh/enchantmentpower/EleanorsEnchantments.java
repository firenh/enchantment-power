package firenh.enchantmentpower;

import java.util.HashMap;
import java.util.Objects;

import firenh.enchantmentpower.polymer.itemcheck.ItemChecks;
import firenh.enchantmentpower.util.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;


public class EleanorsEnchantments implements ModInitializer {
	public static final String MODID = "eleanorsenchantments";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final String MOD_VERSION = "0.0.0";
	private static Config config;

	@Override
	public void onInitialize() {
		loadConfigFromFile();
		ItemChecks.init();
	}

	public static Identifier id(String id) {
		if (Objects.isNull(id)) return null;
		return Identifier.fromNamespaceAndPath(MODID, id);
	}

	public static Identifier vid(String id) {
		if (Objects.isNull(id)) return null;
		return Identifier.fromNamespaceAndPath("minecraft", id);
	}

	public static void log(String info) {
		LOGGER.info(info);
	}

	public static Config getConfig() {
		if (Objects.isNull(config)) {
			loadConfigFromFile();
		}

		return config;
	}

	public static void loadConfigFromFile() {
		config = Config.init();
		if (config.logWhenLoaded) LOGGER.info("Loaded config for " + MODID);
	}

	public static HashMap<Identifier, Integer> getEnchantmentMaxPower() {
		return getConfig().enchantmentMaxPower;
	}
}