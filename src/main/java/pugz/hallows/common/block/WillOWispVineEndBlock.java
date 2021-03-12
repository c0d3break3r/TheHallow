package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.core.registry.HallowsItems;
import pugz.hallows.core.registry.HallowsParticles;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public class WillOWispVineEndBlock extends AbstractTopPlantBlock {
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    protected static final VoxelShape SHAPE = Block.box(4.0D, 14.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape FRUIT_SHAPE = Block.box(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D);
    private final Supplier<Item> fruit;
    private final Supplier<Block> bodyPlant;

    public WillOWispVineEndBlock(AbstractBlock.Properties properties, Supplier<Item> fruit, Supplier<Block> bodyPlant) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
        this.registerDefaultState(this.defaultBlockState().setValue(FRUIT, false));
        this.fruit = fruit;
        this.bodyPlant = bodyPlant;
    }

    @Nonnull
    public BlockState grow(IWorld world) {
        return this.defaultBlockState().setValue(FRUIT, world.getRandom().nextFloat() <= 0.2F).setValue(AGE, world.getRandom().nextInt(25));
    }

    @Nonnull
    @Override
    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        return VoxelShapes.or(SHAPE, state.getValue(FRUIT) ? FRUIT_SHAPE : VoxelShapes.empty());
    }

    protected int getBlocksToGrowWhenBonemealed(Random rand) {
        return PlantBlockHelper.getBlocksToGrowWhenBonemealed(rand);
    }

    @Nonnull
    protected Block getBodyBlock() {
        return bodyPlant.get();
    }

    protected boolean canGrowInto(BlockState state) {
        return PlantBlockHelper.isValidGrowthState(state);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getItemInHand(handIn);
        if (held.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) held.getItem()).getBlock();
            if (block instanceof WillOWispVineStemBlock || block instanceof WillOWispVineEndBlock) return ActionResultType.PASS;
            else if (state.getValue(FRUIT)) {
                ItemStack stack = new ItemStack(fruit.get());
                if (held.isEmpty()) {
                    player.setItemInHand(handIn, stack);
                } else if (!player.addItem(stack)) {
                    player.drop(stack, false);
                }

                worldIn.setBlockAndUpdate(pos, state.setValue(FRUIT, false));
                worldIn.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.NETHER_WART_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
        return ActionResultType.sidedSuccess(worldIn.isClientSide);
    }

    @Override
    public void performBonemeal(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.relative(this.growthDirection);
        int i = Math.min(state.getValue(AGE) + 1, 25);
        int j = this.getBlocksToGrowWhenBonemealed(rand);

        for (int k = 0; k < j && this.canGrowInto(worldIn.getBlockState(blockpos)); ++k) {
            BlockState up = worldIn.getBlockState(blockpos.above());
            boolean flag = up.getBlock() instanceof WillOWispVineStemBlock;
            float fruitChance = 0.1F + (flag ? up.getValue(FRUIT) ? 0.25F : 0.0F : 0.0F);

            worldIn.setBlockAndUpdate(blockpos, state.setValue(AGE, i).setValue(FRUIT, rand.nextFloat() <= fruitChance));
            blockpos = blockpos.relative(this.growthDirection);
            i = Math.min(i + 1, 25);
        }
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
        builder.add(AGE, FRUIT);
    }
}