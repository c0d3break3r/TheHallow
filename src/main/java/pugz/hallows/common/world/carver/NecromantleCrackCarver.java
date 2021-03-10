package pugz.hallows.common.world.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class NecromantleCrackCarver extends WorldCarver<ProbabilityConfig> {
    private final float[] field_202536_i = new float[1024];

    public NecromantleCrackCarver(Codec<ProbabilityConfig> codec) {
        super(codec, 128);
        this.carvableBlocks = ImmutableSet.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.BLACKSTONE, HallowsBlocks.HALLSTONE.get(), HallowsBlocks.HALLOWED_DIRT.get(), HallowsBlocks.DUSK_MORTIS.get(), HallowsBlocks.DAWN_MORTIS.get(), HallowsBlocks.NOON_MORTIS.get(), HallowsBlocks.MIDNIGHT_MORTIS.get());
        this.carvableFluids = ImmutableSet.of(Fluids.WATER, Fluids.LAVA);
    }

    public boolean shouldCarve(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }

    public boolean carveRegion(IChunk chunk, Function<BlockPos, Biome> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ProbabilityConfig config) {
        int i = (this.func_222704_c() * 2 - 1) * 16;
        int mantleYOffset = rand.nextInt(3) + 1;
        double d0 = chunkXOffset * 16 + rand.nextInt(16);
        double d2 = chunkZOffset * 16 + rand.nextInt(16);
        double d1 = rand.nextBoolean() ? rand.nextInt(rand.nextInt(40) + 8) + 20 : chunk.getTopBlockY(Heightmap.Type.WORLD_SURFACE_WG, (int) d0, (int) d2) - mantleYOffset;
        float f = rand.nextFloat() * ((float)Math.PI * 2F);
        float f1 = (rand.nextFloat() - 0.5F) * 2.0F / 8.0F;
        float f2 = (rand.nextFloat() * 2.0F + rand.nextFloat()) * 2.0F;
        int j = i - rand.nextInt(i / 4);
        this.func_227204_a_(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f1, 0, j, 3.0D, carvingMask);
        return true;
    }

    protected boolean func_227208_a_(IChunk chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, double p_227208_14_, double p_227208_16_, BitSet carvingMask) {
        Random random = new Random(seed + (long) chunkX + (long) chunkZ);
        double d0 = chunkX * 16 + 8;
        double d1 = chunkZ * 16 + 8;
        if (!(randOffsetXCoord < d0 - 16.0D - p_227208_14_ * 2.0D) && !(randOffsetZCoord < d1 - 16.0D - p_227208_14_ * 2.0D) && !(randOffsetXCoord > d0 + 16.0D + p_227208_14_ * 2.0D) && !(randOffsetZCoord > d1 + 16.0D + p_227208_14_ * 2.0D)) {
            int i = Math.max(MathHelper.floor(randOffsetXCoord - p_227208_14_) - chunkX * 16 - 1, 0);
            int j = Math.min(MathHelper.floor(randOffsetXCoord + p_227208_14_) - chunkX * 16 + 1, 16);
            int k = Math.max(MathHelper.floor(startY - p_227208_16_) - 1, 1);
            int l = Math.min(MathHelper.floor(startY + p_227208_16_) + 1, this.maxHeight - 8);
            int i1 = Math.max(MathHelper.floor(randOffsetZCoord - p_227208_14_) - chunkZ * 16 - 1, 0);
            int j1 = Math.min(MathHelper.floor(randOffsetZCoord + p_227208_14_) - chunkZ * 16 + 1, 16);
            if (this.func_222700_a(chunk, chunkX, chunkZ, i, j, k, l, i1, j1)) {
                return false;
            } else {
                boolean flag = false;
                BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();
                BlockPos.Mutable blockpos$mutable1 = new BlockPos.Mutable();
                BlockPos.Mutable blockpos$mutable2 = new BlockPos.Mutable();

                for(int k1 = i; k1 < j; ++k1) {
                    int l1 = k1 + chunkX * 16;
                    double d2 = ((double)l1 + 0.5D - randOffsetXCoord) / p_227208_14_;

                    for(int i2 = i1; i2 < j1; ++i2) {
                        int j2 = i2 + chunkZ * 16;
                        double d3 = ((double)j2 + 0.5D - randOffsetZCoord) / p_227208_14_;
                        if (!(d2 * d2 + d3 * d3 >= 1.0D)) {
                            MutableBoolean mutableboolean = new MutableBoolean(false);

                            for(int k2 = l; k2 > k; --k2) {
                                double d4 = ((double)k2 - 0.5D - startY) / p_227208_16_;
                                if (!this.func_222708_a(d2, d4, d3, k2)) {
                                    flag |= this.carveBlock(chunk, biomePos, carvingMask, random, blockpos$mutable, blockpos$mutable1, blockpos$mutable2, seaLevel, chunkX, chunkZ, l1, j2, k1, k2, i2, mutableboolean, startY);
                                }
                            }
                        }
                    }
                }
                return flag;
            }
        } else {
            return false;
        }
    }

    protected boolean carveBlock(IChunk chunk, Function<BlockPos, Biome> biomePos, BitSet carvingMask, Random rand, BlockPos.Mutable p_230358_5_, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int seaLevel, int chunkX, int chunkZ, int posX, int posZ, int p_230358_13_, int posY, int p_230358_15_, MutableBoolean isSurface, double startY) {
        int i = p_230358_13_ | p_230358_15_ << 4 | posY << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            p_230358_5_.setPos(posX, posY, posZ);
            BlockState blockstate = chunk.getBlockState(p_230358_5_);
            BlockState blockstate1 = chunk.getBlockState(p_230358_6_.setAndMove(p_230358_5_, Direction.UP));
            if (!this.canCarveBlock(blockstate, blockstate1)) {
                return false;
            } else {
                chunk.setBlockState(p_230358_5_, Blocks.EMERALD_BLOCK.getDefaultState(), false);
                return true;
            }
        }
    }

    private void func_227204_a_(IChunk chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float caveRadius, float p_227204_15_, float p_227204_16_, int p_227204_17_, int p_227204_18_, double p_227204_19_, BitSet carvingMask) {
        Random random = new Random(seed);
        float height = 2.0F;

        for(int i = 0; i < 256; ++i) {
            if (i == 0 || random.nextInt(3) == 0) {
                height = 2.0F + random.nextFloat() * random.nextFloat();
            }
            this.field_202536_i[i] = height * height;
        }

        float f4 = 0.0F;
        float f1 = 0.0F;

        for(int j = p_227204_17_; j < p_227204_18_; ++j) {
            double d0 = 1.5D + (double)(MathHelper.sin((float)j * (float)Math.PI / (float)p_227204_18_) * (caveRadius / 2.0F));
            double d1 = d0 * p_227204_19_;
            d0 = d0 * ((double)random.nextFloat() * 0.25D + 0.75D);
            d1 = d1 * ((double)random.nextFloat() * 0.25D + 0.75D);
            float f2 = MathHelper.cos(p_227204_16_);
            float f3 = MathHelper.sin(p_227204_16_);
            randOffsetXCoord += (MathHelper.cos(p_227204_15_) * f2);
            startY += f3;
            randOffsetZCoord += (MathHelper.sin(p_227204_15_) * f2);
            p_227204_16_ = p_227204_16_ * 0.7F;
            p_227204_16_ = p_227204_16_ + f1 * 0.05F;
            p_227204_15_ += f4 * 0.05F;
            f1 = f1 * 0.8F;
            f4 = f4 * 0.5F;
            f1 = f1 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 2.0F;
            f4 = f4 + (random.nextFloat() - random.nextFloat()) * random.nextFloat() * 4.0F;
            if (random.nextInt(4) != 0) {
                if (!this.func_222702_a(chunkX, chunkZ, randOffsetXCoord, randOffsetZCoord, j, p_227204_18_, caveRadius)) {
                    return;
                }

                this.func_227208_a_(chunk, biomePos, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, d0, d1, carvingMask);
            }
        }
    }

    protected boolean func_222708_a(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return (p_222708_1_ * p_222708_1_ + p_222708_5_ * p_222708_5_) * (double)this.field_202536_i[p_222708_7_ - 1] + p_222708_3_ * p_222708_3_ / 6.0D >= 1.0D;
    }
}