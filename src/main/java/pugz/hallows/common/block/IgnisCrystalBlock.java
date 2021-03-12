package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class IgnisCrystalBlock extends Block implements IWaterLoggable {
    protected static final VoxelShape STEM_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape FLOWER_SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 6.0D, 14.0D);
    public static final EnumProperty<DoubleBlockHalf> HALF = BlockStateProperties.DOUBLE_BLOCK_HALF;
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public IgnisCrystalBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(HALF, DoubleBlockHalf.UPPER).setValue(WATERLOGGED, false));
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.getValue(HALF) == DoubleBlockHalf.UPPER ? FLOWER_SHAPE : STEM_SHAPE;
    }

    public void setPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        if (worldIn.getBlockState(pos.below()).getBlock() == this) {
            if (worldIn.getBlockState(pos.below()).getValue(HALF) == DoubleBlockHalf.UPPER) worldIn.setBlock(pos.below(), worldIn.getBlockState(pos.below()).setValue(HALF, DoubleBlockHalf.LOWER), 3);
        }
    }

    @Override
    public void playerWillDestroy(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.playerWillDestroy(worldIn, pos, state, player);

        if (worldIn.getBlockState(pos.below()).getBlock() == this) {
            if (worldIn.getBlockState(pos.below()).getValue(HALF) == DoubleBlockHalf.LOWER) worldIn.setBlock(pos.below(), worldIn.getBlockState(pos.below()).setValue(HALF, DoubleBlockHalf.UPPER), 3);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState().setValue(WATERLOGGED, context.getLevel().getFluidState(context.getClickedPos()).is(FluidTags.WATER));
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.canSurvive(worldIn, currentPos)) {
            worldIn.getBlockTicks().scheduleTick(currentPos, this, 1);
        } return worldIn.isEmptyBlock(currentPos.below()) ? Blocks.AIR.defaultBlockState() : super.updateShape(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @SuppressWarnings("deprecation")
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return canSupportCenter(worldIn, pos.below(), Direction.UP) || worldIn.getBlockState(pos.below()).getBlock() == this;
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }
}