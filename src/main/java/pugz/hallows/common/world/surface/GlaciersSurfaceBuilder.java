package pugz.hallows.common.world.surface;

import com.google.common.collect.ImmutableList;
import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.SharedSeedRandom;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.PerlinNoiseGenerator;
import net.minecraft.world.gen.SimplexNoiseGenerator;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilderConfig;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.IntStream;

public class GlaciersSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    private static final SurfaceBuilderConfig HALLOWED_DIRT_CONFIG = new SurfaceBuilderConfig(HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState(), HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState(), HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState());
    private static final SurfaceBuilderConfig COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG = new SurfaceBuilderConfig(Blocks.COARSE_DIRT.defaultBlockState(), Blocks.DIRT.defaultBlockState(), HallowsBlocks.HALLOWED_DIRT.get().defaultBlockState());
    protected BlockState[] field_215432_a;
    protected long seed;
    protected PerlinNoiseGenerator perlin1;
    protected PerlinNoiseGenerator perlin2;
    protected SimplexNoiseGenerator simplex3;

    public GlaciersSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    public void apply(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise > 1.0D) {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, HALLOWED_DIRT_CONFIG);
        } else if (noise > -0.6D) {
            SurfaceBuilder.DEFAULT.apply(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG);
        } else {
            double d0 = 0.0D;
            double d1 = Math.min(Math.abs(noise), this.perlin1.getValue((double) x * 0.25D, (double) z * 0.25D, true) * 15.0D);
            if (d1 > 0.0D) {
                double d3 = Math.abs(this.perlin2.getValue((double) x * 0.001953125D, (double) z * 0.001953125D, true));
                d0 = d1 * d1 * 2.5D;
                double d4 = Math.ceil(d3 * 50.0D) + 14.0D;
                if (d0 > d4) {
                    d0 = d4;
                }

                d0 = d0 + 64.0D;
            }

            int i1 = x & 15;
            int i = z & 15;
            BlockState blockstate3 = HallowsBlocks.HALLSTONE.get().defaultBlockState();
            ISurfaceBuilderConfig isurfacebuilderconfig = biomeIn.getGenerationSettings().getSurfaceBuilderConfig();
            BlockState blockstate4 = isurfacebuilderconfig.getUnderMaterial();
            BlockState blockstate = isurfacebuilderconfig.getTopMaterial();
            BlockState blockstate1 = blockstate4;
            int j = (int) (noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
            boolean flag = Math.cos(noise / 3.0D * Math.PI) > 0.0D;
            int k = -1;
            boolean flag1 = false;
            BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

            for (int l = Math.max(startHeight, (int) d0 + 1); l >= 0; --l) {
                blockpos$mutable.set(i1, l, i);
                if (l < (int) d0) {
                    chunkIn.setBlockState(blockpos$mutable, defaultBlock, false);
                }

                BlockState blockstate2 = chunkIn.getBlockState(blockpos$mutable);
                if (blockstate2.isAir()) {
                    k = -1;
                } else if (blockstate2.is(defaultBlock.getBlock())) {
                    if (k == -1) {
                        flag1 = false;
                        if (j <= 0) {
                            blockstate3 = Blocks.AIR.defaultBlockState();
                            blockstate1 = defaultBlock;
                        } else if (l >= seaLevel - 4 && l <= seaLevel + 1) {
                            blockstate3 = HallowsBlocks.MIDNIGHT_MORTIS.get().defaultBlockState();
                            blockstate1 = blockstate4;
                        }

                        if (l < seaLevel && blockstate3.isAir()) {
                            blockstate3 = defaultFluid;
                        }

                        k = j + Math.max(0, l - seaLevel);
                        if (l >= seaLevel - 1) {
                            if (l <= seaLevel + 3 + j) {
                                chunkIn.setBlockState(blockpos$mutable, blockstate, false);
                                flag1 = true;
                            } else {
                                BlockState blockstate5;
                                if (l >= 64 && l <= 127) {
                                    if (flag) {
                                        blockstate5 = HallowsBlocks.MIDNIGHT_MORTIS.get().defaultBlockState();
                                    } else {
                                        blockstate5 = this.func_215431_a(x, l, z);
                                    }
                                } else {
                                    blockstate5 = HallowsBlocks.DUSK_MORTIS.get().defaultBlockState();
                                }

                                chunkIn.setBlockState(blockpos$mutable, blockstate5, false);
                            }
                        } else if (l < seaLevel - 7 - j) {
                            blockstate1 = defaultBlock;
                            chunkIn.setBlockState(blockpos$mutable, HallowsBlocks.NOON_MORTIS.get().defaultBlockState(), false);
                        } else {
                            chunkIn.setBlockState(blockpos$mutable, blockstate1, false);
                            Block block = blockstate1.getBlock();
                            if (block == HallowsBlocks.NOON_MORTIS.get() || block == HallowsBlocks.HALLSTONE.get() || block == HallowsBlocks.DAWN_MORTIS.get() || block == HallowsBlocks.DUSK_MORTIS.get() || block == HallowsBlocks.MIDNIGHT_MORTIS.get()) {
                                chunkIn.setBlockState(blockpos$mutable, HallowsBlocks.NOON_MORTIS.get().defaultBlockState(), false);
                            }
                        }
                    } else if (k > 0) {
                        --k;
                        if (flag1) {
                            chunkIn.setBlockState(blockpos$mutable, HallowsBlocks.NOON_MORTIS.get().defaultBlockState(), false);
                        } else {
                            chunkIn.setBlockState(blockpos$mutable, this.func_215431_a(x, l, z), false);
                        }
                    }
                }
            }
        }
    }

    public void setSeed(long seed) {
        if (this.seed != seed || this.field_215432_a == null) {
            this.func_215430_b(seed);
        }

        if (this.seed != seed || this.perlin1 == null || this.perlin2 == null) {
            SharedSeedRandom sharedseedrandom = new SharedSeedRandom(seed);
            this.perlin1 = new PerlinNoiseGenerator(sharedseedrandom, IntStream.rangeClosed(-3, 0));
            this.perlin2 = new PerlinNoiseGenerator(sharedseedrandom, ImmutableList.of(0));
        }

        this.seed = seed;
    }

    protected void func_215430_b(long p_215430_1_) {
        this.field_215432_a = new BlockState[64];
        Arrays.fill(this.field_215432_a, HallowsBlocks.DAWN_MORTIS.get().defaultBlockState());
        SharedSeedRandom sharedseedrandom = new SharedSeedRandom(p_215430_1_);
        this.simplex3 = new SimplexNoiseGenerator(sharedseedrandom);

        for(int l1 = 0; l1 < 64; ++l1) {
            l1 += sharedseedrandom.nextInt(5) + 1;
            if (l1 < 64) {
                this.field_215432_a[l1] = HallowsBlocks.DUSK_MORTIS.get().defaultBlockState();
            }
        }

        int i2 = sharedseedrandom.nextInt(4) + 2;

        for(int i = 0; i < i2; ++i) {
            int j = sharedseedrandom.nextInt(3) + 1;
            int k = sharedseedrandom.nextInt(64);

            for(int l = 0; k + l < 64 && l < j; ++l) {
                this.field_215432_a[k + l] = HallowsBlocks.NOON_MORTIS.get().defaultBlockState();
            }
        }

        int j2 = sharedseedrandom.nextInt(4) + 2;

        for(int k2 = 0; k2 < j2; ++k2) {
            int i3 = sharedseedrandom.nextInt(3) + 2;
            int l3 = sharedseedrandom.nextInt(64);

            for(int i1 = 0; l3 + i1 < 64 && i1 < i3; ++i1) {
                this.field_215432_a[l3 + i1] = HallowsBlocks.NOON_MORTIS.get().defaultBlockState();
            }
        }

        int l2 = sharedseedrandom.nextInt(4) + 2;

        for(int j3 = 0; j3 < l2; ++j3) {
            int i4 = sharedseedrandom.nextInt(3) + 1;
            int k4 = sharedseedrandom.nextInt(64);

            for(int j1 = 0; k4 + j1 < 64 && j1 < i4; ++j1) {
                this.field_215432_a[k4 + j1] = HallowsBlocks.DUSK_MORTIS.get().defaultBlockState();
            }
        }

        int k3 = sharedseedrandom.nextInt(3) + 3;
        int j4 = 0;

        for(int l4 = 0; l4 < k3; ++l4) {
            j4 += sharedseedrandom.nextInt(16) + 4;

            for(int k1 = 0; j4 + k1 < 64 && k1 < 1; ++k1) {
                this.field_215432_a[j4 + k1] = HallowsBlocks.DUSK_MORTIS.get().defaultBlockState();
                if (j4 + k1 > 1 && sharedseedrandom.nextBoolean()) {
                    this.field_215432_a[j4 + k1 - 1] = HallowsBlocks.MIDNIGHT_MORTIS.get().defaultBlockState();
                }

                if (j4 + k1 < 63 && sharedseedrandom.nextBoolean()) {
                    this.field_215432_a[j4 + k1 + 1] = HallowsBlocks.MIDNIGHT_MORTIS.get().defaultBlockState();
                }
            }
        }
    }

    protected BlockState func_215431_a(int p_215431_1_, int p_215431_2_, int p_215431_3_) {
        int i = (int)Math.round(this.simplex3.getValue((double)p_215431_1_ / 512.0D, (double)p_215431_3_ / 512.0D, (double)p_215431_2_ / 512.0D) * 2.0D);
        return this.field_215432_a[(p_215431_2_ + i + 64) % 64];
    }
}
