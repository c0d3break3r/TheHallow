package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
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
import pugz.hallows.core.registry.HallowsParticles;

import javax.annotation.Nonnull;
import java.util.Random;
import java.util.function.Supplier;

public class WillOWispVineEndBlock extends AbstractTopPlantBlock {
    public static final BooleanProperty FRUIT = BooleanProperty.create("fruit");
    protected static final VoxelShape SHAPE = Block.makeCuboidShape(4.0D, 14.0D, 4.0D, 12.0D, 16.0D, 12.0D);
    protected static final VoxelShape FRUIT_SHAPE = Block.makeCuboidShape(4.0D, 6.0D, 4.0D, 12.0D, 14.0D, 12.0D);
    private final Supplier<Item> fruit;
    private final Supplier<Block> bodyPlant;

    public WillOWispVineEndBlock(AbstractBlock.Properties properties, Supplier<Item> fruit, Supplier<Block> bodyPlant) {
        super(properties, Direction.DOWN, SHAPE, false, 0.1D);
        this.setDefaultState(this.getDefaultState().with(FRUIT, false));
        this.fruit = fruit;
        this.bodyPlant = bodyPlant;
    }

    @Nonnull
    public BlockState grow(IWorld world) {
        return this.getDefaultState().with(FRUIT, world.getRandom().nextFloat() <= 0.2F).with(AGE, world.getRandom().nextInt(25));
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
        return bodyPlant.get();
    }

    protected boolean canGrowIn(BlockState state) {
        return PlantBlockHelper.isAir(state);
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(handIn);
        if (held.getItem() instanceof BlockItem) {
            Block block = ((BlockItem) held.getItem()).getBlock();
            if (block instanceof WillOWispVineStemBlock || block instanceof WillOWispVineEndBlock) return ActionResultType.PASS;
            else if (state.get(FRUIT)) {
                ItemStack stack = new ItemStack(fruit.get());
                if (held.isEmpty()) {
                    player.setHeldItem(handIn, stack);
                } else if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }

                worldIn.setBlockState(pos, state.with(FRUIT, false));
                worldIn.playSound(null, (double)pos.getX() + 0.5D, (double)pos.getY() + 0.5D, (double)pos.getZ() + 0.5D, SoundEvents.BLOCK_NETHER_WART_BREAK, SoundCategory.BLOCKS, 1.0F, 1.0F);
            }
        }
        return ActionResultType.func_233537_a_(worldIn.isRemote);
    }

    @Override
    public void grow(ServerWorld worldIn, Random rand, BlockPos pos, BlockState state) {
        BlockPos blockpos = pos.offset(this.growthDirection);
        int i = Math.min(state.get(AGE) + 1, 25);
        int j = this.getGrowthAmount(rand);

        for (int k = 0; k < j && this.canGrowIn(worldIn.getBlockState(blockpos)); ++k) {
            BlockState up = worldIn.getBlockState(blockpos.up());
            boolean flag = up.getBlock() instanceof WillOWispVineStemBlock;
            float fruitChance = 0.1F + (flag ? up.get(FRUIT) ? 0.25F : 0.0F : 0.0F);

            worldIn.setBlockState(blockpos, state.with(AGE, i).with(FRUIT, rand.nextFloat() <= fruitChance));
            blockpos = blockpos.offset(this.growthDirection);
            i = Math.min(i + 1, 25);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.get(FRUIT)) {
            double d0 = (double) pos.getX() + 0.5D;
            double d1 = (double) pos.getY() + 0.7D;
            double d2 = (double) pos.getZ() + 0.5D;
            worldIn.addParticle(HallowsParticles.ORANGE_WILL_O_WISP.get(), d0, d1, d2, 0.0D, 0.0D, 0.0D);
        }
    }

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(AGE, FRUIT);
    }
}