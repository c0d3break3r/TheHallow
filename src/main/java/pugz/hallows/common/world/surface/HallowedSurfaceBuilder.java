package pugz.hallows.common.world.surface;

import com.mojang.serialization.Codec;
import java.util.Random;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunk;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;
import pugz.hallows.core.registry.HallowsBlocks;

public class HallowedSurfaceBuilder extends SurfaceBuilder<SurfaceBuilderConfig> {
    private static final SurfaceBuilderConfig HALLOWED_DIRT_CONFIG = new SurfaceBuilderConfig(HallowsBlocks.HALLOWED_DIRT.get().getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState());
    private static final SurfaceBuilderConfig COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG = new SurfaceBuilderConfig(Blocks.COARSE_DIRT.getDefaultState(), Blocks.DIRT.getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState());
    private static final SurfaceBuilderConfig GRASS_DIRT_HALLOWED_DIRT_CONFIG = new SurfaceBuilderConfig(Blocks.GRASS_BLOCK.getDefaultState(), Blocks.DIRT.getDefaultState(), HallowsBlocks.HALLOWED_DIRT.get().getDefaultState());
    public static final SurfaceBuilderConfig PETRIFIED_SAND_CONFIG = new SurfaceBuilderConfig(HallowsBlocks.PETRIFIED_SAND.get().getDefaultState(), HallowsBlocks.PETRIFIED_SANDSTONE.get().getDefaultState(), HallowsBlocks.PETRIFIED_SANDSTONE.get().getDefaultState());

    public HallowedSurfaceBuilder(Codec<SurfaceBuilderConfig> codec) {
        super(codec);
    }

    public void buildSurface(Random random, IChunk chunkIn, Biome biomeIn, int x, int z, int startHeight, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceBuilderConfig config) {
        if (noise > 1.75D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, HALLOWED_DIRT_CONFIG);
        } else if (noise > -0.95D) {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, COARSE_DIRT_DIRT_HALLOWED_DIRT_CONFIG);
        } else {
            SurfaceBuilder.DEFAULT.buildSurface(random, chunkIn, biomeIn, x, z, startHeight, noise, defaultBlock, defaultFluid, seaLevel, seed, GRASS_DIRT_HALLOWED_DIRT_CONFIG);
        }
    }
}
