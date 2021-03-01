package pugz.hallows.common.world.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.KelpTopBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.Random;

public class SpecterKelpFeature extends Feature<NoFeatureConfig> {
    public SpecterKelpFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }

    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos pos, NoFeatureConfig config) {
        int i = 0;
        int j = reader.getHeight(Heightmap.Type.OCEAN_FLOOR, pos.getX(), pos.getZ());
        BlockPos blockpos = new BlockPos(pos.getX(), j, pos.getZ());
        if (reader.getBlockState(blockpos).isIn(Blocks.WATER)) {
            BlockState blockstate = HallowsBlocks.SPECTER_KELP.get().getDefaultState();
            BlockState blockstate1 = HallowsBlocks.SPECTER_KELP_PLANT.get().getDefaultState();
            int k = 1 + rand.nextInt(10);

            for(int l = 0; l <= k; ++l) {
                if (reader.getBlockState(blockpos).isIn(Blocks.WATER) && reader.getBlockState(blockpos.up()).isIn(Blocks.WATER) && blockstate1.isValidPosition(reader, blockpos)) {
                    if (l == k) {
                        reader.setBlockState(blockpos, blockstate.with(KelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                        ++i;
                    } else {
                        reader.setBlockState(blockpos, blockstate1, 2);
                    }
                } else if (l > 0) {
                    BlockPos blockpos1 = blockpos.down();
                    if (blockstate.isValidPosition(reader, blockpos1) && !reader.getBlockState(blockpos1.down()).isIn(Blocks.KELP)) {
                        reader.setBlockState(blockpos1, blockstate.with(KelpTopBlock.AGE, rand.nextInt(4) + 20), 2);
                        ++i;
                    }
                    break;
                }
                blockpos = blockpos.up();
            }
        }
        return i > 0;
    }
}
