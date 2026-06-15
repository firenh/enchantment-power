//package firenh.enchantmentpower.util;
//
//import java.io.BufferedWriter;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//
//import com.google.common.collect.Maps;
//import com.google.gson.Gson;
//
//import com.google.gson.GsonBuilder;
//import com.google.gson.Strictness;
//import firenh.enchantmentpower.EnchantmentPower;
//import net.minecraft.core.Holder;
//import net.minecraft.resources.Identifier;
//import net.minecraft.world.item.enchantment.Enchantment;
//
//public class Config {
//    private static Gson gson = new GsonBuilder().setPrettyPrinting().setStrictness(Strictness.LENIENT).create();
//
//    //Config Default Values
//
//    public String CONFIG_VERSION_DO_NOT_TOUCH_PLS = EnchantmentPower.MOD_VERSION;
//
//    public boolean logWhenLoaded = true;
//    public HashMap<Identifier, Integer> enchantmentMaxPower = EnchantmentsConfig.getDefaultConfig();
//
//    public Debug debug = new Debug();
//
//    // public Advanced advancedSettings = new Advanced();
//
//    //~~~~~~~~
//
//    public static class Debug {
//        // public boolean enableMultinoiseCommand = false;
//    }
//
//    public static Config init() {
//        Config config = null;
//
//        try {
//            Path configPath = Paths.get("", "config", "enchantment_power.json");
//
//            if (Files.exists(configPath)) {
//                config = gson.fromJson(
//                        new FileReader(configPath.toFile()),
//                        Config.class
//                );
//
//                if (!config.CONFIG_VERSION_DO_NOT_TOUCH_PLS.equals(EnchantmentPower.MOD_VERSION)) {
//                    config.CONFIG_VERSION_DO_NOT_TOUCH_PLS = EnchantmentPower.MOD_VERSION;
//
//                    BufferedWriter writer = new BufferedWriter(
//                            new FileWriter(configPath.toFile())
//                    );
//
//                    writer.write(gson.toJson(config));
//                    writer.close();
//                }
//
//            } else {
//                config = new Config();
//                Paths.get("", "config").toFile().mkdirs();
//
//                BufferedWriter writer = new BufferedWriter(
//                        new FileWriter(configPath.toFile())
//                );
//
//                writer.write(gson.toJson(config));
//                writer.close();
//            }
//
//
//        } catch (IOException exception) {
//            exception.printStackTrace();
//        }
//
//        return config;
//    }
//}