package firenh.enchantmentpower.config;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.google.gson.Gson;

import com.google.gson.GsonBuilder;
import firenh.enchantmentpower.EnchantmentPower;
import net.minecraft.resources.Identifier;

public class EPConfig {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    //Config Default Values

    public int CONFIG_VERSION_DO_NOT_TOUCH_PLS = EnchantmentPower.CONFIG_VERSION;

    public int default_cost = ConfigDefaults.DEFAULT;
    public HashMap<String, Integer> enchantment_powers = ConfigDefaults.getDefaultEnchantmentConfig();

    //~~~~~~~~

    private List<String> defaultServerIcons() {
        ArrayList<String> list = new ArrayList<>();
        list.add("server-icon.png");
        return list;
    }

    public static EPConfig init() {
        EPConfig config = null;

        try {
            Path configPath = Paths.get("", "config", EnchantmentPower.MODID + ".json");

            if (Files.exists(configPath)) {
                config = gson.fromJson(
                        new FileReader(configPath.toFile()),
                        EPConfig.class
                );

                if (config.CONFIG_VERSION_DO_NOT_TOUCH_PLS != EnchantmentPower.CONFIG_VERSION) {
                    config.CONFIG_VERSION_DO_NOT_TOUCH_PLS = EnchantmentPower.CONFIG_VERSION;

                    BufferedWriter writer = new BufferedWriter(
                            new FileWriter(configPath.toFile())
                    );

                    writer.write(gson.toJson(config));
                    writer.close();
                }

            } else {
                config = new EPConfig();
                Paths.get("", "config").toFile().mkdirs();

                BufferedWriter writer = new BufferedWriter(
                        new FileWriter(configPath.toFile())
                );

                writer.write(gson.toJson(config));
                writer.close();
            }


        } catch (IOException exception) {
            exception.printStackTrace();
        }

        return config;
    }
}