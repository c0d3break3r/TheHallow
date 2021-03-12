package pugz.hallows.common.world.feature;

import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

import java.util.Random;

public class SpreadOreFeature extends Feature<OreFeatureConfig> {
    public SpreadOreFeature() {
        super(OreFeatureConfig.CODEC);
    }

    @Override
    public boolean place(ISeedReader worldIn, ChunkGenerator generator, Random rand, BlockPos pos, OreFeatureConfig config) {
        Direction ew = rand.nextBoolean() ? Direction.EAST : Direction.WEST;
        Direction ns = rand.nextBoolean() ? Direction.NORTH : Direction.SOUTH;
        Direction ud = rand.nextBoolean() ? Direction.UP : Direction.DOWN;
        Direction[] directions = new Direction[] {ew, ns, ud};
        BlockPos.Mutable blockpos$mutable = pos.mutable();

        for (int i = 0; i < rand.nextInt(config.size) + 1; ++i) {
            blockpos$mutable.setWithOffset(blockpos$mutable, directions[rand.nextInt(3)]);
            if (rand.nextBoolean()) {
                blockpos$mutable.setWithOffset(blockpos$mutable, directions[rand.nextInt(3)]);
                if (rand.nextBoolean()) {
                    blockpos$mutable.setWithOffset(blockpos$mutable, directions[rand.nextInt(3)]);
                }
            }

            if (config.target.test(worldIn.getBlockState(blockpos$mutable), rand) && !this.isNextToAir(worldIn, blockpos$mutable)) {
                worldIn.setBlock(blockpos$mutable, config.state, 2);
            }
        }
        return true;
    }

    private boolean isNextToAir(IWorld world, BlockPos pos) {
        BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
        for(Direction direction : Direction.values()) {
            blockpos$mutable.setWithOffset(pos, direction);
            if (world.isEmptyBlock(blockpos$mutable)) {
                return true;
            }
        }
        return false;
    }
}