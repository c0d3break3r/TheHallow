package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsItems;

import javax.annotation.Nonnull;
import java.util.Random;

public class WillOWispFruitBlock extends AbstractTopPlantBlock {
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 4.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape FRUIT_SHAPE = Block.makeCuboidShape(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D);

    public WillOWispFruitBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
        this.setDefaultState(this.getDefaultState().with(FRUIT, false));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.or(SHAPE, state.get(FRUIT) ? FRUIT_SHAPE : VoxelShapes.empty());
    }

    protected int getGrowthAmount(Random rand) {
        return PlantBlockHelper.getGrowthAmount(rand);
    }

    @Nonnull
    protected Block getBodyPlantBlock() {
        return HallowsBlocks.WILL_O_WISP_VINES.get();
    }

    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(FRUIT)) {
            ItemStack stack = new ItemStack(HallowsItems.ORANGE_WILL_O_WISP_BERRY.get());
            if (player.getHeldItem(handIn).isEmpty()) {
                player.setHeldItem(handIn, stack);
            } else if (!player.addItemStackToInventory(stack)) {
                player.dropItem(stack, false);
            }

            worldIn.setBlockState(pos, state.with(FRUIT, false));
        }
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, FRUIT);
    }
}