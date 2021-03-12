package pugz.hallows.common.world.carver;

import com.google.common.collect.ImmutableSet;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import org.apache.commons.lang3.mutable.MutableBoolean;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.BitSet;
import java.util.Random;
import java.util.function.Function;

public class HallowsCaveCarver extends WorldCarver<ProbabilityConfig> {
    private final float[] field_202536_i = new float[1024];

    public HallowsCaveCarver(Codec<ProbabilityConfig> codec, int maxHeight) {
        super(codec, maxHeight);
        this.replaceableBlocks = ImmutableSet.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.GRASS_BLOCK, Blocks.BLACKSTONE, HallowsBlocks.HALLSTONE.get(), HallowsBlocks.HALLOWED_DIRT.get(), HallowsBlocks.DUSK_MORTIS.get(), HallowsBlocks.DAWN_MORTIS.get(), HallowsBlocks.NOON_MORTIS.get(), HallowsBlocks.MIDNIGHT_MORTIS.get());
        this.liquids = ImmutableSet.of(Fluids.WATER);
    }

    @Override
    public boolean isStartChunk(Random rand, int chunkX, int chunkZ, ProbabilityConfig config) {
        return rand.nextFloat() <= config.probability;
    }

    protected int func_230361_b_(Random random) {
        return random.nextInt(random.nextInt(120) + 8);
    }

    protected float func_230359_a_(Random random) {
        float f = random.nextFloat() * 4.0F + random.nextFloat();
        if (random.nextInt(8) == 0) {
            f *= random.nextFloat() * random.nextFloat() * 4.0F + 1.0F;
        }

        return f;
    }

    protected void func_227205_a_(IChunk chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float p_227205_14_, double p_227205_15_, BitSet carvingMask) {
        double d0 = 1.5D + (double)(MathHelper.sin(((float)Math.PI / 2.0F)) * p_227205_14_);
        double d1 = d0 * p_227205_15_;
        this.carveSphere(chunk, biomePos, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord + 1.0D, startY, randOffsetZCoord, d0, d1, carvingMask);
    }

    public boolean carve(IChunk chunk, Function<BlockPos, Biome> biomePos, Random rand, int seaLevel, int chunkXOffset, int chunkZOffset, int chunkX, int chunkZ, BitSet carvingMask, ProbabilityConfig config) {
        int i = (this.getRange() * 2 - 1) * 16;
        int j = rand.nextInt(rand.nextInt(rand.nextInt(15) + 1) + 1);

        for(int k = 0; k < j; ++k) {
            double d0 = (chunkXOffset * 16 + rand.nextInt(16));
            double d1 = (double)this.func_230361_b_(rand) * 1.5D;
            double d2 = (chunkZOffset * 16 + rand.nextInt(16));
            int l = 1;
            if (rand.nextInt(4) == 0) {
                float f1 = 1.0F + rand.nextFloat() * 6.0F;
                this.func_227205_a_(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f1, 0.5D, carvingMask);
                l += rand.nextInt(4);
            }

            for(int k1 = 0; k1 < l; ++k1) {
                float f = rand.nextFloat() * ((float)Math.PI * 2F);
                float f3 = (rand.nextFloat() - 0.5F) / 4.0F;
                float f2 = this.func_230359_a_(rand) * 2.0F;
                int i1 = i - rand.nextInt(i / 4);
                this.func_227206_a_(chunk, biomePos, rand.nextLong(), seaLevel, chunkX, chunkZ, d0, d1, d2, f2, f, f3, 0, i1, 1.5D, carvingMask);
            }
        }
        return true;
    }

    protected boolean carveBlock(IChunk chunk, Function<BlockPos, Biome> biomePos, BitSet carvingMask, Random rand, BlockPos.Mutable place, BlockPos.Mutable p_230358_6_, BlockPos.Mutable p_230358_7_, int seaLevel, int chunkX, int chunkZ, int posX, int posZ, int p_230358_13_, int posY, int p_230358_15_, MutableBoolean isSurface) {
        int i = p_230358_13_ | p_230358_15_ << 4 | posY << 8;
        if (carvingMask.get(i)) {
            return false;
        } else {
            carvingMask.set(i);
            place.set(posX, posY, posZ);
            BlockState blockstate = chunk.getBlockState(place);
            BlockState blockstate1 = chunk.getBlockState(p_230358_6_.setWithOffset(place, Direction.UP));
            if (blockstate.is(Blocks.GRASS_BLOCK)) {
                isSurface.setTrue();
            }

            if (!this.canCarveBlock(blockstate, blockstate1) || blockstate.is(Blocks.EMERALD_BLOCK)) {
                return false;
            } else {
                if (posY < 8) {
                    chunk.setBlockState(place, LAVA.createLegacyBlock(), false);
                } else {
                    chunk.setBlockState(place, CAVE_AIR, false);
                    if (isSurface.isTrue()) {
                        p_230358_7_.setWithOffset(place, Direction.DOWN);
                        if (chunk.getBlockState(p_230358_7_).is(Blocks.DIRT)) {
                            chunk.setBlockState(p_230358_7_, biomePos.apply(place).getGenerationSettings().getSurfaceBuilderConfig().getTopMaterial(), false);
                        }
                    }
                }
                return true;
            }
        }
    }

    protected boolean canCarveBlock(BlockState state, BlockState aboveState) {
        return this.canReplaceBlock(state) || (state.is(Blocks.SAND) || state.is(Blocks.GRAVEL)) && !aboveState.getFluidState().is(FluidTags.WATER) && !state.is(Blocks.EMERALD_BLOCK);
    }

    protected void func_227206_a_(IChunk chunk, Function<BlockPos, Biome> biomePos, long seed, int seaLevel, int chunkX, int chunkZ, double randOffsetXCoord, double startY, double randOffsetZCoord, float caveRadius, float pitch, float p_227206_16_, int p_227206_17_, int p_227206_18_, double p_227206_19_, BitSet carvingMask) {
        Random random = new Random(seed);
        int i = random.nextInt(p_227206_18_ / 2) + p_227206_18_ / 4;
        float f1 = 0.0F;
        float f4 = 0.0F;
        float height = 1.0F;

        for(int i1 = 0; i1 < 256; ++i1) {
            if (i1 == 0 || random.nextInt(4) == 0) {
                height = 1.0F + random.nextFloat();
            }
            this.field_202536_i[i1] = height * height;
        }

        for(int j = p_227206_17_; j < p_227206_18_; ++j) {
            double d0 = 1.5D + (double)(MathHelper.sin((float)j * (float)Math.PI / (float)p_227206_18_) * caveRadius);
            double d1 = d0 * p_227206_19_;
            d0 *= ((double)random.nextFloat() * 0.25D + 0.75D);
            d1 *= ((double)random.nextFloat() * 0.25D + 0.75D);
            float f2 = MathHelper.cos(p_227206_16_);
            float f3 = MathHelper.sin(p_227206_16_);
            randOffsetXCoord += (MathHelper.cos(pitch) * f2);
            startY += f3;
            randOffsetZCoord += (MathHelper.sin(pitch) * f2);
            p_227206_16_ *= 0.7F;
            p_227206_16_ += f1 * 0.05F;
            pitch += f4 * 0.05F;
            if (j == i && caveRadius > 1.0F) {
                this.func_227206_a_(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5F + 0.5F, pitch - ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.5D, carvingMask);
                this.func_227206_a_(chunk, biomePos, random.nextLong(), seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, random.nextFloat() * 0.5F + 0.5F, pitch + ((float)Math.PI / 2F), p_227206_16_ / 3.0F, j, p_227206_18_, 1.5D, carvingMask);
                return;
            }

            if (random.nextInt(4) != 0) {
                if (!this.canReach(chunkX, chunkZ, randOffsetXCoord, randOffsetZCoord, j, p_227206_18_, caveRadius)) {
                    return;
                }

                this.carveSphere(chunk, biomePos, seed, seaLevel, chunkX, chunkZ, randOffsetXCoord, startY, randOffsetZCoord, d0, d1, carvingMask);
            }
        }
    }

    protected boolean skip(double p_222708_1_, double p_222708_3_, double p_222708_5_, int p_222708_7_) {
        return (p_222708_1_ * p_222708_1_ + p_222708_5_ * p_222708_5_) * (double)this.field_202536_i[p_222708_7_ - 1] + p_222708_3_ * p_222708_3_ / 6.0D >= 1.0D;
    }
}