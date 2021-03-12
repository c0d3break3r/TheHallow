package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Direction;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.core.registry.HallowsItems;
import pugz.hallows.core.registry.HallowsParticles;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public class WillOWispVineStemBlock extends AbstractBodyPlantBlock {
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    private final Supplier<Item> fruit;
    private final Supplier<Block> topPlant;

    public WillOWispVineStemBlock(AbstractBlock.Properties properties, Supplier<Item> fruit, Supplier<Block> topPlant) {
        super(properties, Direction.DOWN, SHAPE, false);
        this.registerDefaultState(this.defaultBlockState().setValue(FRUIT, false));
        this.fruit = fruit;
        this.topPlant = topPlant;
    }

    @Nonnull
    protected AbstractTopPlantBlock getHeadBlock() {
        return (AbstractTopPlantBlock) topPlant.get();
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (state.getValue(FRUIT)) {
            ItemStack stack = new ItemStack(fruit.get());
            if (player.getItemInHand(handIn).isEmpty()) {
                player.setItemInHand(handIn, stack);
            } else if (!player.addItem(stack)) {
                player.drop(stack, false);
            }

            worldIn.setBlockAndUpdate(pos, state.setValue(FRUIT, false));
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(FRUIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.7D;
            double d2 = (double) pos.getZ() + 0.5D;
            IParticleData particle = this.fruit == HallowsItems.PURPLE_WILL_O_WISP_BERRY ? HallowsParticles.PURPLE_WILL_O_WISP.get() :
                    this.fruit == HallowsItems.BLUE_WILL_O_WISP_BERRY ? HallowsParticles.BLUE_WILL_O_WISP.get() :
                            this.fruit == HallowsItems.YELLOW_WILL_O_WISP_BERRY ? HallowsParticles.YELLOW_WILL_O_WISP.get() : HallowsParticles.ORANGE_WILL_O_WISP.get();

            worldIn.addParticle(particle, d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FRUIT);
    }
}