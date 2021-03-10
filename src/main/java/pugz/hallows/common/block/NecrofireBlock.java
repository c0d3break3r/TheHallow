package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import pugz.hallows.core.registry.other.HallowsTags;

import javax.annotation.Nonnull;

public class NecrofireBlock extends AbstractFireBlock {
    public NecrofireBlock(AbstractBlock.Properties properties) {
        super(properties, 1.5F);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
    }

    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return isNecrofireBase(worldIn.getBlockState(pos.down()).getBlock());
    }

    public static boolean isNecrofireBase(Block block) {
        return block.isIn(HallowsTags.Blocks.NECROFIRE_BASE_BLOCKS);
    }

    protected boolean canBurn(BlockState state) {
        return true;
    }
}