package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class WillOWispVineStemBlock extends AbstractBodyPlantBlock {
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private final Supplier<Item> fruit;
    private final Supplier<Block> topPlant;

    public WillOWispVineStemBlock(AbstractBlock.Properties properties, Supplier<Item> fruit, Supplier<Block> topPlant) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.setDefaultState(this.getDefaultState().with(FRUIT, false));
        this.fruit = fruit;
        this.topPlant = topPlant;
    }

    @Nonnull
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) topPlant.get();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.get(FRUIT)) {
            ItemStack stack = new ItemStack(fruit.get());
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
        builder.add(FRUIT);
    }
}