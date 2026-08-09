package firenh.enchantmentpower;

import java.util.Map;
import java.util.Objects;

import firenh.enchantmentpower.config.ConfigGetter;
import firenh.enchantmentpower.polymer.itemcheck.ItemChecks;
import firenh.enchantmentpower.config.EPConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;


public class EnchantmentPower implements ModInitializer {
	public static final String MODID = "enchantmentpower";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final String MOD_VERSION = "0.0.0";
	public static final int CONFIG_VERSION = 1;
	private static EPConfig config;

	@Override
	public void onInitialize() {
		loadConfigFromFile();
		ItemChecks.init();

//		log("h: " + ConfigGetter.getMaxPower(vid("sharpness")));
	}

	public static Identifier id(String id) {;
		return Identifier.fromNamespaceAndPath(MODID, id);
	}

	public static Identifier vid(String id) {
		if (Objects.isNull(id)) return null;
		return Identifier.fromNamespaceAndPath("minecraft", id);
	}

	public static void log(String info) {
		LOGGER.info(info);
	}

	public static EPConfig getConfig() {
		if (Objects.isNull(config)) {
			loadConfigFromFile();
		}

		return config;
	}

	public static void loadConfigFromFile() {
		config = EPConfig.init();
		if (true) LOGGER.info("Loaded config for " + MODID);
	}
//
//	public static HashMap<Identifier, Integer> getEnchantmentMaxPower() {
//		return getConfig().enchantmentMaxPower;
//	}
}