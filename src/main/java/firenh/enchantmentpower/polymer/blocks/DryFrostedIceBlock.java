package firenh.enchantmentpower.polymer.blocks;
// package firenh.eleanorsenchantments.polymer.blocks;

// import eu.pb4.polymer.core.api.block.PolymerBlock;
// import net.minecraft.core.BlockPos;
// import net.minecraft.world.level.Level;
// import net.minecraft.world.level.block.Blocks;
// import net.minecraft.world.level.block.FrostedIceBlock;
// import net.minecraft.world.level.block.state.BlockState;

// public class DryFrostedIceBlock extends FrostedIceBlock implements PolymerBlock {
//     public DryFrostedIceBlock(Properties settings) {
//         super(settings);
//     }

//     public static BlockState meltsInto() {
// 		return Blocks.AIR.defaultBlockState();
// 	}

//     @Override
//     protected void melt(BlockState state, Level world, BlockPos pos) {
//         world.removeBlock(pos, false);
//         world.neighborChanged(pos, meltsInto().getBlock(), pos);
// 	}

//     @Override
//     public BlockState getPolymerBlockState(BlockState state) {
//         return Blocks.FROSTED_ICE.defaultBlockState().setValue(FrostedIceBlock.AGE, state.getValue(AGE));
//     }
    
// }
