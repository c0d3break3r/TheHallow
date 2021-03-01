package pugz.hallows.common.world.feature;

import com.google.common.collect.Lists;
import net.minecraft.block.Blocks;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.vector.Vector3i;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import org.apache.commons.lang3.tuple.Pair;
import pugz.hallows.common.world.feature.config.GeodeFeatureConfig;
import pugz.hallows.common.world.noise.NormalNoise;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GeodeFeature extends Feature<GeodeFeatureConfig> {
    public GeodeFeature() {
        super(GeodeFeatureConfig.CODEC);
    }

    @Override
    @SuppressWarnings("deprecation")
    public boolean generate(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, GeodeFeatureConfig config) {
        int minGenOffset = config.minGenOffset;
        int maxGenOffset = config.maxGenOffset;
        if (world.getBlockState(pos.add(0, maxGenOffset / 3, 0)).isAir() || (world.getBlockState(pos.down()).getFluidState().isTagged(FluidTags.WATER) && world.getBiome(pos).getCategory() == Biome.Category.OCEAN)) {
            return false;
        } else {
            List<Pair<BlockPos, Integer>> var8 = Lists.newLinkedList();
            int var9 = config.minDistribution + rand.nextInt(config.maxDistribution - config.minDistribution);
            NormalNoise noise = NormalNoise.a(new SharedSeedRandom(world.getSeed()), -4, 1.0);
            List<BlockPos> var12 = Lists.newLinkedList();
            double var13 = (double)var9 / (double)config.maxOuterDistance;
            double var18 = 1.0D / Math.sqrt(1.7D);
            double var20 = 1.0D / Math.sqrt(2.2D + var13);
            double var22 = 1.0D / Math.sqrt(3.2D + var13);
            double var24 = 1.0D / Math.sqrt(4.2D + var13);
            double var26 = 1.0D / Math.cbrt(2.0D + rand.nextDouble() / 2.0D + (var9 > 3 ? var13 : 0.0D));
            boolean var28 = (double)rand.nextFloat() < 0.95D;

            int var29;
            int var30;
            for(var29 = 0; var29 < var9; ++var29) {
                var30 = config.minOuterDistance + rand.nextInt(config.maxOuterDistance - config.minOuterDistance);
                int var31 = config.minOuterDistance + rand.nextInt(config.maxOuterDistance - config.minOuterDistance);
                int var32 = config.minOuterDistance + rand.nextInt(config.maxOuterDistance - config.minOuterDistance);
                var8.add(Pair.of(pos.add(var30, var31, var32), config.minOffset + rand.nextInt(config.maxOffset - config.minOffset)));
            }

            if (var28) {
                var29 = rand.nextInt(4);
                var30 = var9 * 2 + 1;
                // var30 must be the four corners of the geode
                if (var29 == 0) {
                    var12.add(pos.add(var30, 7, 0));
                    var12.add(pos.add(var30, 5, 0));
                    var12.add(pos.add(var30, 1, 0));
                } else if (var29 == 1) {
                    var12.add(pos.add(0, 7, var30));
                    var12.add(pos.add(0, 5, var30));
                    var12.add(pos.add(0, 1, var30));
                } else if (var29 == 2) {
                    var12.add(pos.add(var30, 7, var30));
                    var12.add(pos.add(var30, 5, var30));
                    var12.add(pos.add(var30, 1, var30));
                } else {
                    var12.add(pos.add(0, 7, 0));
                    var12.add(pos.add(0, 5, 0));
                    var12.add(pos.add(0, 1, 0));
                }
            }

            List<BlockPos> POSITIONS = Lists.newArrayList();
            Iterator var41 = BlockPos.getAllInBox(pos.add(minGenOffset, minGenOffset, minGenOffset), pos.add(maxGenOffset, maxGenOffset, maxGenOffset)).iterator();

            while(true) {
                while(true) {
                    double var34;
                    double var36;
                    BlockPos var43;
                    do {
                        if (!var41.hasNext()) {
                            return true;
                        }

                        var43 = (BlockPos)var41.next();
                        // noise is applied to change shape? (Try removing)
                        double var45 = noise.a(var43.getX(), var43.getY(), var43.getZ()) * config.noiseMultiplier;
                        var34 = 0.0D;
                        var36 = 0.0D;

                        Iterator var38;
                        Pair var39;
                        for(var38 = var8.iterator(); var38.hasNext(); var34 += MathHelper.fastInvSqrt(var43.distanceSq((Vector3i)var39.getLeft()) + (double)(Integer)var39.getRight()) + var45) {
                            var39 = (Pair)var38.next();
                        }

                        BlockPos var51;
                        for(var38 = var12.iterator(); var38.hasNext(); var36 += MathHelper.fastInvSqrt(var43.distanceSq(var51) + (double)2) + var45) {
                            var51 = (BlockPos)var38.next();
                        }
                    } while(var34 < var24);

                    if (var28 && var36 >= var26 && var34 < var18) {
                        world.setBlockState(var43, Blocks.AIR.getDefaultState(), 3);
                    } else if (var34 >= var18) {
                        world.setBlockState(var43, Blocks.AIR.getDefaultState(), 3);
                    } else if (var34 >= var20) {
                        boolean var49 = (double)rand.nextFloat() < config.alternateLayer0Chance;
                        if (var49) {
                            world.setBlockState(var43, HallowsBlocks.HALLSTONE.get().getDefaultState(), 3);
                        } else {
                            world.setBlockState(var43, HallowsBlocks.HALLSTONE.get().getDefaultState(), 3);
                        }

                        if ((!config.requireLayer0Alternate || var49) && (double)rand.nextFloat() < config.potentialPlacementsChance) {
                            POSITIONS.add(var43.toImmutable());
                        }
                    } else if (var34 >= var22) {
                        world.setBlockState(var43, HallowsBlocks.HALLSTONE.get().getDefaultState(), 3);
                    } else if (var34 >= var24) {
                        world.setBlockState(var43, HallowsBlocks.HALLSTONE.get().getDefaultState(), 3);
                    }
                }
            }
        }
    }
}