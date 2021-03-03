package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.Items;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.state.properties.Half;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;
import java.util.Random;

public class GiantCauldronBlock extends Block {
    public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;
    public static final EnumProperty<Half> HALF = BlockStateProperties.HALF;
    public static final BooleanProperty ACTIVATED = BooleanProperty.create("activated");
    private static final VoxelShape SOUTH = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D), Block.makeCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape SOUTH_FOOT = Block.makeCuboidShape(12.0D, 0.0D, 12.0D, 16.0D, 4.0D, 16.0D);
    private static final VoxelShape NORTH = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.makeCuboidShape(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D));
    private static final VoxelShape NORTH_FOOT = Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 4.0D, 4.0D);
    private static final VoxelShape EAST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.makeCuboidShape(4.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape EAST_FOOT = Block.makeCuboidShape(0.0D, 0.0D, 12.0D, 4.0D, 4.0D, 16.0D);
    private static final VoxelShape WEST = VoxelShapes.or(Block.makeCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D), Block.makeCuboidShape(12.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D));
    private static final VoxelShape WEST_FOOT = Block.makeCuboidShape(12.0D, 0.0D, 0.0D, 16.0D, 4.0D, 4.0D);
    private static final VoxelShape BOTTOM = Block.makeCuboidShape(0.0D, 4.0D, 0.0D, 16.0D, 6.0D, 16.0D);
    private static final VoxelShape PORTAL = Block.makeCuboidShape(0.0D, 12.0D, 0.0D, 16.0D, 14.0D, 16.0D);

    public GiantCauldronBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(HALF, Half.BOTTOM).with(ACTIVATED, false));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (player.getHeldItem(handIn).getItem() == Items.FLINT_AND_STEEL) {
            worldIn.setBlockState(pos, state.with(ACTIVATED, true), 3);
            return ActionResultType.SUCCESS;
        } else return ActionResultType.PASS;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        switch (state.get(FACING)) {
            default:
            case SOUTH:
                return state.get(HALF) == Half.BOTTOM ? VoxelShapes.or(SOUTH, BOTTOM, SOUTH_FOOT) : state.get(ACTIVATED) ? VoxelShapes.or(SOUTH, PORTAL) : SOUTH;
            case NORTH:
                return state.get(HALF) == Half.BOTTOM ? VoxelShapes.or(NORTH, BOTTOM, NORTH_FOOT) : state.get(ACTIVATED) ? VoxelShapes.or(NORTH, PORTAL) : NORTH;
            case WEST:
                return state.get(HALF) == Half.BOTTOM ? VoxelShapes.or(WEST, BOTTOM, WEST_FOOT) : state.get(ACTIVATED) ? VoxelShapes.or(WEST, PORTAL) : WEST;
            case EAST:
                return state.get(HALF) == Half.BOTTOM ? VoxelShapes.or(EAST, BOTTOM, EAST_FOOT) : state.get(ACTIVATED) ? VoxelShapes.or(EAST, PORTAL) : EAST;
        }
    }

    protected static Direction getFacing(BlockItemUseContext context) {
        float f = MathHelper.wrapDegrees(context.getPlacementYaw()) / 45.0F;

        if (f > -2.0F && f <= 0.0F) {
            return Direction.NORTH;
        } else if (f > 0.0F && f <= 2.0F) {
            return Direction.WEST;
        } else if (f > 2.0F) {
            return Direction.SOUTH;
        } else {
            return Direction.EAST;
        }
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.with(FACING, rotation.rotate(state.get(FACING)));
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public BlockState mirror(BlockState state, Mirror mirror) {
        Direction.Axis axis = state.get(FACING).getAxis();
        if (mirror != Mirror.NONE) {
            if ((mirror == Mirror.FRONT_BACK && axis == Direction.Axis.X) || (mirror == Mirror.LEFT_RIGHT && axis == Direction.Axis.Z)) {
                return state.rotate(Rotation.COUNTERCLOCKWISE_90);
            }
            else return state.rotate(Rotation.CLOCKWISE_90);
        } else return state;
    }

    public BlockState getStateForPlacement(BlockItemUseContext context) {
        BlockPos pos = context.getPos();
        BlockState down = context.getWorld().getBlockState(pos.down());
        BlockState up = context.getWorld().getBlockState(pos.up());

        if (down.getBlock() instanceof GiantCauldronBlock && down.get(HALF) == Half.BOTTOM) {
            return this.getDefaultState().with(FACING, down.get(FACING)).with(HALF, Half.TOP);
        } else if (up.getBlock() instanceof GiantCauldronBlock && up.get(HALF) == Half.TOP) {
            return this.getDefaultState().with(FACING, up.get(FACING)).with(HALF, Half.BOTTOM);
        }

        return this.getDefaultState().with(FACING, getFacing(context)).with(HALF, MathHelper.sin(context.getPlayer().getPitch(1.0F) * ((float) Math.PI / 180.0F)) > 0.0F ? Half.BOTTOM : Half.TOP);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(ACTIVATED)) {
            if (rand.nextInt(100) == 0) {
                worldIn.playSound((double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, SoundEvents.BLOCK_PORTAL_AMBIENT, SoundCategory.BLOCKS, 0.5F, rand.nextFloat() * 0.4F + 0.8F, false);
            }

            for (int i = 0; i < 4; ++i) {
                double d0 = (double) pos.getX() + rand.nextDouble();
                double d1 = (double) pos.getY() + rand.nextDouble();
                double d2 = (double) pos.getZ() + rand.nextDouble();
                double d3 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d4 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                double d5 = ((double) rand.nextFloat() - 0.5D) * 0.5D;
                int j = rand.nextInt(2) * 2 - 1;
                if (!worldIn.getBlockState(pos.west()).isIn(this) && !worldIn.getBlockState(pos.east()).isIn(this)) {
                    d0 = (double) pos.getX() + 0.5D + 0.25D * (double) j;
                    d3 = (double) (rand.nextFloat() * 2.0F * (float) j);
                } else {
                    d2 = (double) pos.getZ() + 0.5D + 0.25D * (double) j;
                    d5 = (double) (rand.nextFloat() * 2.0F * (float) j);
                }

                worldIn.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }

    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING, HALF, ACTIVATED);
    }
}