package firenh.enchantmentpower.polymer.blocks;
// package firenh.eleanorsenchantments.polymer.blocks;

// import firenh.eleanorsenchantments.EleanorsEnchantments;
// import net.minecraft.core.Registry;
// import net.minecraft.core.registries.BuiltInRegistries;
// import net.minecraft.world.entity.EntityType;
// import net.minecraft.world.level.block.Block;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.SoundType;
// import net.minecraft.world.level.block.state.BlockBehaviour;
// import net.minecraft.world.level.material.MapColor;

// public class EEBlocks {
//     public static final Block DRY_FROSTED_ICE = register("dry_frosted_ice", new DryFrostedIceBlock(
//         BlockBehaviour.Properties.of()
//             .mapColor(MapColor.ICE)
//             .friction(0.98F)
//             .strength(0.5F)
//             .sound(SoundType.GLASS)
//             .noOcclusion()
//             .isValidSpawn((state, world, pos, entityType) -> entityType == EntityType.POLAR_BEAR)
//             .isRedstoneConductor(Blocks::never)
//         )
//     );

//     public static void init() {
//         new EEBlocks();
//     }

//     public static Block register(String id, Block block) {
//         return Registry.register(BuiltInRegistries.BLOCK, EleanorsEnchantments.id(id), block);
//     }
// }
