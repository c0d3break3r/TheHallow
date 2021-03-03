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
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.common.Tags;

import javax.annotation.Nonnull;

public class HemlockBlock extends Block implements IWaterLoggable {
    protected static final VoxelShape LOWER_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape UPPER_SHAPE = Block.makeCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 8.0D, 12.0D);
    public static final EnumProperty<TripleBlockHalf> HALF = EnumProperty.create("half", TripleBlockHalf.class);
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public HemlockBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(HALF, TripleBlockHalf.UPPER).with(WATERLOGGED, false));
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return state.get(HALF) == TripleBlockHalf.UPPER ? UPPER_SHAPE : LOWER_SHAPE;
    }

    public void onBlockPlacedBy(World worldIn, BlockPos pos, BlockState state, LivingEntity placer, ItemStack stack) {
        BlockState down = worldIn.getBlockState(pos.down());
        if (down.getBlock() == this) {
            if (down.get(HALF) == TripleBlockHalf.UPPER)
                worldIn.setBlockState(pos.down(), down.with(HALF, TripleBlockHalf.MIDDLE), 3);
        }
    }

    @Override
    public void onBlockHarvested(World worldIn, BlockPos pos, BlockState state, PlayerEntity player) {
        super.onBlockHarvested(worldIn, pos, state, player);
        BlockState down = worldIn.getBlockState(pos.down());

        if (down.getBlock() == this) {
            if (down.get(HALF) == TripleBlockHalf.MIDDLE || down.get(HALF) == TripleBlockHalf.LOWER)
                worldIn.setBlockState(pos.down(), down.with(HALF, TripleBlockHalf.UPPER), 3);
        }
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (!stateIn.isValidPosition(worldIn, currentPos)) {
            worldIn.getPendingBlockTicks().scheduleTick(currentPos, this, 1);
        } return worldIn.getBlockState(currentPos.down()).isAir() ? Blocks.AIR.getDefaultState() : super.updatePostPlacement(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStillFluidState(false) : super.getFluidState(state);
    }

    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        BlockState down = worldIn.getBlockState(pos.down());
        return hasEnoughSolidSide(worldIn, pos.down(), Direction.UP) || down.getBlock() == state.getBlock() || down.isIn(Tags.Blocks.DIRT);
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(WATERLOGGED, context.getWorld().getFluidState(context.getPos()).isTagged(FluidTags.WATER));
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(HALF, WATERLOGGED);
    }

    public enum TripleBlockHalf implements IStringSerializable {
        UPPER("upper"),
        MIDDLE("middle"),
        LOWER("lower");

        private final String name;

        TripleBlockHalf(String name) {
            this.name = name;
        }

        @Nonnull
        public String getString() {
            return name;
        }
    }

}