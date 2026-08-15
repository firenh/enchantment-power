package firenh.enchantmentpower.registry;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.EnchantmentPower;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;

import java.util.HashMap;

public class EPEnchantmentTags {
    public static HashMap<Integer, TagKey<Enchantment>> enchantmentKeys = new HashMap<>();
    public static HashMap<Integer, TagKey<Item>> itemKeys = new HashMap<>();

    public static final TagKey<Enchantment> NEVER_EXCEED_MAX_LEVEL = eof("never_exceed_max_level");

    public static TagKey<Enchantment> getEnchantmentTag(int i) {
        if (enchantmentKeys.containsKey(i)) {
            return enchantmentKeys.get(i);
        }

        TagKey<Enchantment> tag = eof(tagPath("cost/cost_", i));
        enchantmentKeys.put(i, tag);

//        EnchantmentPower.log("Tag: " + tag.toString());


        return tag;

    }

    public static TagKey<Item> getItemTag(int i) {
        if (itemKeys.containsKey(i)) {
            return itemKeys.get(i);
        }

        TagKey<Item> tag = iof(tagPath("cap/cap_", i));
        itemKeys.put(i, tag);

        return tag;
    }

    public static String tagPath(String path, int i) {
        return path + i;
    }

    @SuppressWarnings("null")
    private static TagKey<Enchantment> eof(String id) {
        return TagKey.create(Registries.ENCHANTMENT, EnchantmentPower.id(id));
    }

    private static TagKey<Item> iof(String id) {
        return TagKey.create(Registries.ITEM, EnchantmentPower.id(id));
    }
}
