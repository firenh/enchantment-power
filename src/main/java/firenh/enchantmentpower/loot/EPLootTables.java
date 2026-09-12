package firenh.enchantmentpower.loot;

import firenh.enchantmentpower.EnchantmentPower;
import net.fabricmc.fabric.api.event.registry.DynamicRegistries;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.NestedLootTable;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;

public class EPLootTables {
    public static final ResourceKey<LootTable> OVERENCHANTED_NORMAL = customLootTable("util/overenchanted_normal");
    public static final ResourceKey<LootTable>  OVERENCHANTED_NETHERITE = customLootTable("util/overenchanted_netherite");
    public static final ResourceKey<LootTable>  OVERENCHANTED_TRIDENT = customLootTable("util/overenchanted_trident");

    public static void init() {
        addToLootTable("chests/ruined_portal", OVERENCHANTED_NETHERITE, 0.008f);
        addToLootTable("chests/trial_chambers/reward", OVERENCHANTED_TRIDENT, 0.01f);
        addToLootTable("chests/jungle_temple", OVERENCHANTED_NORMAL, 0.01f);
        addToLootTable("chests/buried_treasure", OVERENCHANTED_TRIDENT, 0.018f);

        addToLootTable("chests/abandoned_mineshaft", OVERENCHANTED_NORMAL, 0.022f);
        addToLootTable("chests/desert_pyramid", OVERENCHANTED_NORMAL, 0.024f);
        addToLootTable("chests/simple_dungeon", OVERENCHANTED_TRIDENT, 0.028f);
        addToLootTable("chests/pillager_outpost", OVERENCHANTED_NORMAL, 0.035f);


        addToLootTable("chests/nether_bridge", OVERENCHANTED_NETHERITE, 0.06f);
        addToLootTable("chests/bastion_other", OVERENCHANTED_NETHERITE, 0.065f);
        addToLootTable("chests/bastion_hoglin_stable", OVERENCHANTED_NETHERITE, 0.065f);
        addToLootTable("chests/bastion_bridge", OVERENCHANTED_NETHERITE, 0.65f);
        addToLootTable("chests/ancient_city", OVERENCHANTED_NETHERITE, 0.125f);
        addToLootTable("chests/end_city_treasure", OVERENCHANTED_NORMAL, 0.175f);
        addToLootTable("chests/trial_chambers/reward_ominous", OVERENCHANTED_TRIDENT, 0.275f);
        addToLootTable("chests/woodland_mansion", OVERENCHANTED_NORMAL, 0.275f);
        addToLootTable("chests/stronghold_corridor", OVERENCHANTED_NORMAL, 0.275f);
        addToLootTable("chests/stronghold_crossing", OVERENCHANTED_NORMAL, 0.5f);
        addToLootTable("chests/bastion_treasure", OVERENCHANTED_NETHERITE, 0.5f);

        addToPool("archaeology/trail_ruins_rare", OVERENCHANTED_TRIDENT, 1);
    }

    private static void addToLootTable(String targetTable, ResourceKey<LootTable> addedTable, float chance) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, provider) -> {
            if (key.identifier().equals(EnchantmentPower.vid(targetTable)) && source.isBuiltin()) {
                LootPool.Builder pool = LootPool.lootPool()
                        .add(NestedLootTable.lootTableReference(provider.getOrThrow(addedTable)))
                        .when(LootItemRandomChanceCondition.randomChance(chance));
                tableBuilder.withPool(pool);
            }
        });
    }

    private static void addToPool(String targetTable, ResourceKey<LootTable> addedTable, int weight) {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, provider) -> {
            if (key.identifier().equals(EnchantmentPower.vid(targetTable)) && source.isBuiltin()) {
                tableBuilder.modifyPools(pool -> {
                    pool.add(NestedLootTable.lootTableReference(provider.getOrThrow(addedTable)).setWeight(weight));
                });
            }
        });
    }

    private static ResourceKey<LootTable> customLootTable(String id) {
        return lootTableKey(EnchantmentPower.id(id));
    }

    private static ResourceKey<LootTable> vanillaId(String id) {
        return lootTableKey(EnchantmentPower.vid(id));
    }

    private static ResourceKey<LootTable> lootTableKey(Identifier id) {
        return ResourceKey.create(Registries.LOOT_TABLE, id);
    }
}
