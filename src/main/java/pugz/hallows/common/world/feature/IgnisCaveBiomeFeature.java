package pugz.hallows.common.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import pugz.hallows.common.block.IgnisCrystalBlock;
import pugz.hallows.common.block.IgnisCrystalFlowerBlock;
import pugz.hallows.common.world.feature.config.CaveBiomeFeatureConfig;
import pugz.hallows.core.registry.HallowsBlocks;

import java.util.Random;

public class IgnisCaveBiomeFeature extends AbstractCaveBiomeFeature {
    public IgnisCaveBiomeFeature() {
        super(CaveBiomeFeatureConfig.CODEC);
    }

    @Override
    public void placeCeiling(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, Direction direction, CaveBiomeFeatureConfig config) {
        if (world.getBlockState(pos.offset(direction)).getBlock() == Blocks.CAVE_AIR && world.getBlockState(pos.offset(direction.getOpposite())).getBlock() != Blocks.CAVE_AIR) {
            if (config.ceilingState != null) world.setBlockState(pos, config.ceilingState, 2);
            if (config.fillerState != null) world.setBlockState(pos.offset(direction.getOpposite()), config.fillerState, 2);
        }
    }

    @Override
    public void placeWall(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, Direction direction, CaveBiomeFeatureConfig config) {
        if (world.getBlockState(pos.offset(direction)).getBlock() == Blocks.CAVE_AIR && world.getBlockState(pos.offset(Direction.UP)).getBlock() != Blocks.CAVE_AIR) {
            if (config.wallState != null) world.setBlockState(pos, config.wallState, 2);
            if (config.fillerState != null) world.setBlockState(pos.offset(direction.getOpposite()), config.fillerState, 2);
        }
    }

    @Override
    public void placeFloor(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, Direction direction, CaveBiomeFeatureConfig config) {
        if (world.getBlockState(pos.offset(direction)).getBlock() == Blocks.CAVE_AIR && world.getBlockState(pos.offset(direction.getOpposite())).getBlock() != Blocks.CAVE_AIR) {
            if (config.floorState != null) world.setBlockState(pos, config.floorState, 2);
            if (config.fillerState != null) world.setBlockState(pos.offset(direction.getOpposite()), config.fillerState, 2);

            if (rand.nextFloat() <= config.featureChance) {
                generateColumn(world, rand, pos.up());
            }
        }
    }

    private void generateColumn(ISeedReader reader, Random rand, BlockPos pos) {
        int height = rand.nextInt(rand.nextInt(5) + 12) + 1;

        BlockPos.Mutable place = pos.toMutable();
        int maxHeight = place.getY() + height;

        if (isAir(reader, place, maxHeight)) {
            while (place.getY() <= maxHeight) {
                boolean waterlogged = reader.getFluidState(place).isTagged(FluidTags.WATER);

                if (place.getY() <= (int)(maxHeight * 0.5F)) {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_BLOCK.get().getDefaultState(), 3);
                } else if (place.getY() <= (int)(maxHeight * 0.75F)) {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_STEM.get().getDefaultState().with(IgnisCrystalBlock.WATERLOGGED, waterlogged), 3);
                } else {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_FLOWER.get().getDefaultState().with(IgnisCrystalFlowerBlock.HALF, place.getY() < pos.getY() + height ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER).with(IgnisCrystalFlowerBlock.WATERLOGGED, waterlogged), 3);
                }

                place.move(Direction.UP);
            }
        }
    }

    private boolean isAir(ISeedReader reader, BlockPos.Mutable pos, int maxHeight) {
        while (pos.getY() <= maxHeight) {
            if (!reader.isAirBlock(pos) && pos.getY() < maxHeight) return false;
        }
        return true;
    }
}