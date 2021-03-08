package pugz.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import pugz.hallows.common.block.HemlockBlock;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.Random;

public class SpreadPlantFeature extends Feature<NoFeatureConfig> {
    public SpreadPlantFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        Iterable<BlockPos> positions = BlockPos.getRandomPositions(rand, rand.nextInt(8) + 8, pos.getX() - 3, pos.getY() - 1, pos.getZ() - 3, pos.getX() + 3, pos.getY() + 1, pos.getZ() + 3);

        for (BlockPos p : positions) {
            Block block = HallowsBlocks.HEMLOCK.get();
            if (block.getDefaultState().isValidPosition(reader, p.down())) {
                int height = rand.nextInt(2) + 2;

                for (int y = 0; y <= height; ++y) {
                    if (!reader.isAirBlock(p.up(y))) return false;
                }

                for (int y = 0; y <= height; ++y) {
                    boolean topHalf = y == height;
                    boolean bottomHalf = y == 0;
                    reader.setBlockState(p.up(y), block.getDefaultState().with(HemlockBlock.HALF, topHalf ? HemlockBlock.TripleBlockHalf.UPPER : bottomHalf ? HemlockBlock.TripleBlockHalf.LOWER : HemlockBlock.TripleBlockHalf.MIDDLE).with(HemlockBlock.WATERLOGGED, reader.getFluidState(p).isTagged(FluidTags.WATER)), 3);
                }
            }
        }

        return true;
    }
}