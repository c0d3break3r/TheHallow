package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nonnull;
import java.util.Random;

public class HangingLeavesBlock extends Block implements IGrowable {
    public static final EnumProperty<Half> HALF = EnumProperty.create("half", Half.class);
    protected static final VoxelShape LARGE_SHAPE = Block.box(1, 0, 1, 15, 16, 15);
    protected static final VoxelShape SMALL_SHAPE = Block.box(4, 0, 4, 12, 16, 12);

    public HangingLeavesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, Half.SMALL));
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState();
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient) {
        return worldIn.getBlockState(pos.below()).isAir();
    }

    @Override
    public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        worldIn.setBlockAndUpdate(pos.below(), this.defaultBlockState());
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState stateUp = worldIn.getBlockState(pos.above());
        return stateUp.getBlock() == this || stateUp.is(BlockTags.LEAVES) || stateUp.is(BlockTags.LOGS);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            return super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getValue(HALF) == Half.LARGE ? LARGE_SHAPE : SMALL_SHAPE;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getCollisionShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.empty();
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF);
    }

    @Override
    @SuppressWarnings("deprecation")
    public void neighborChanged(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (worldIn.getBlockState(pos.below()).getBlock() == this) {
            worldIn.setBlockAndUpdate(pos, state.setValue(HALF, Half.LARGE));
        } else {
            worldIn.setBlockAndUpdate(pos, state.setValue(HALF, Half.SMALL));
        }
    }

    public enum Half implements IStringSerializable {
        LARGE,
        SMALL;

        public String getSerializedName() {
            return this == LARGE ? "large" : "small";
        }
    }
}