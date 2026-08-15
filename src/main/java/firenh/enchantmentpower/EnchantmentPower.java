package firenh.enchantmentpower;

import java.util.Map;
import java.util.Objects;

import firenh.enchantmentpower.gamerule.EPGamerules;
import firenh.enchantmentpower.loot.EPLootTables;
import firenh.enchantmentpower.polymer.itemcheck.ItemChecks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;


public class EnchantmentPower implements ModInitializer {
	public static final String MODID = "enchantmentpower";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final String MOD_VERSION = "0.0.0";

	@Override
	public void onInitialize() {
		ItemChecks.init();
		EPLootTables.init();
		EPGamerules.init();
	}

	public static Identifier id(String id) {
		;
		return Identifier.fromNamespaceAndPath(MODID, id);
	}

	public static Identifier vid(String id) {
		if (Objects.isNull(id)) return null;
		return Identifier.fromNamespaceAndPath("minecraft", id);
	}

	public static void log(String info) {
		LOGGER.info(info);
	}

}