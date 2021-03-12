package pugz.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.AbstractTopPlantBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.other.HallowsTags;

import java.util.Random;

public class WillOWispVinesFeature extends Feature<NoFeatureConfig> {
    public WillOWispVinesFeature() {
        super(NoFeatureConfig.CODEC);
    }

    public boolean place(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        if (!reader.isEmptyBlock(pos)) {
            return false;
        } else {
            BlockState blockstate = reader.getBlockState(pos.above());
            if (!blockstate.is(HallowsTags.Blocks.BASE_STONE_HALLOWS) || !blockstate.is(HallowsBlocks.HALLOWED_DIRT.get()) || !blockstate.is(Blocks.COARSE_DIRT) || !blockstate.is(Blocks.DIRT) || !blockstate.is(Blocks.GRASS_BLOCK)) {
                return false;
            } else {
                this.func_236428_a_(reader, rand, pos);
                this.func_236429_b_(reader, rand, pos);
                return true;
            }
        }
    }

    private void func_236428_a_(IWorld world, Random random, BlockPos pos) {
        world.setBlock(pos, HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState(), 2);
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();

        for (int i = 0; i < 200; ++i) {
            blockpos$mutable.setWithOffset(pos, random.nextInt(6) - random.nextInt(6), random.nextInt(2) - random.nextInt(5), random.nextInt(6) - random.nextInt(6));
            if (world.isEmptyBlock(blockpos$mutable)) {
                int j = 0;

                for (Direction direction : Direction.values()) {
                    BlockState blockstate = world.getBlockState(blockpos$mutable1.setWithOffset(blockpos$mutable, direction));
                    if (blockstate.is(HallowsTags.Blocks.BASE_STONE_HALLOWS) || blockstate.is(HallowsBlocks.HALLOWED_DIRT.get()) || blockstate.is(Blocks.COARSE_DIRT) || blockstate.is(Blocks.DIRT) || blockstate.is(Blocks.GRASS_BLOCK)) {
                        ++j;
                    }

                    if (j > 1) break;
                }

                if (j == 1) {
                    world.setBlock(blockpos$mutable, HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState(), 2);
                }
            }
        }
    }

    private void func_236429_b_(IWorld world, Random random, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

        for(int i = 0; i < 100; ++i) {
            blockpos$mutable.setWithOffset(pos, random.nextInt(8) - random.nextInt(8), random.nextInt(2) - random.nextInt(7), random.nextInt(8) - random.nextInt(8));
            if (world.isEmptyBlock(blockpos$mutable)) {
                BlockState blockstate = world.getBlockState(blockpos$mutable.above());
                if (blockstate.is(HallowsTags.Blocks.BASE_STONE_HALLOWS) || blockstate.is(HallowsBlocks.HALLOWED_DIRT.get()) || blockstate.is(Blocks.COARSE_DIRT) || blockstate.is(Blocks.DIRT) || blockstate.is(Blocks.GRASS_BLOCK)) {
                    int j = MathHelper.nextInt(random, 1, 8);
                    if (random.nextInt(6) == 0) j *= 2;
                    if (random.nextInt(5) == 0) j = 1;

                    func_236427_a_(world, random, blockpos$mutable, j, 17, 25);
                }
            }
        }
    }

    public static void func_236427_a_(IWorld world, Random random, BlockPos.Mutable blockpos$mutable, int p_236427_3_, int p_236427_4_, int p_236427_5_) {
        for(int i = 0; i <= p_236427_3_; ++i) {
            if (world.isEmptyBlock(blockpos$mutable) /*|| world.getBlockState(blockpos$mutable).isIn(Blocks.WATER) */) {
                //boolean waterlogged = world.getBlockState(blockpos$mutable).isIn(Blocks.WATER);
                if (i == p_236427_3_ || !world.isEmptyBlock(blockpos$mutable.below())) {
                    world.setBlock(blockpos$mutable, HallowsBlocks.ORANGE_WILL_O_WISP_VINES_END.get().defaultBlockState().setValue(AbstractTopPlantBlock.AGE, MathHelper.nextInt(random, p_236427_4_, p_236427_5_)), 2);
                    break;
                }
                world.setBlock(blockpos$mutable, HallowsBlocks.ORANGE_WILL_O_WISP_VINES.get().defaultBlockState(), 2);
            }

            blockpos$mutable.move(Direction.DOWN);
        }
    }
}
