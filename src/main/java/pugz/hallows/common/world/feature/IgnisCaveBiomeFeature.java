package pugz.hallows.common.world.feature;

import net.minecraft.block.Blocks;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import pugz.hallows.common.block.IgnisCrystalBlock;
import pugz.hallows.common.world.feature.config.CaveBiomeFeatureConfig;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsFeatures;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

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

            if (rand.nextFloat() <= config.featureChance / 1.5F) {
                HallowsFeatures.Configured.ORE_GILDED_TENEBRITE.generate(world, generator, rand, pos);
            }
        }
    }

    @Override
    public void placeFloor(ISeedReader world, ChunkGenerator generator, Random rand, BlockPos pos, Direction direction, CaveBiomeFeatureConfig config) {
        if (world.getBlockState(pos.offset(direction)).getBlock() == Blocks.CAVE_AIR && world.getBlockState(pos.offset(direction.getOpposite())).getBlock() != Blocks.CAVE_AIR) {
            if (config.floorState != null) world.setBlockState(pos, config.floorState, 2);
            if (config.fillerState != null) world.setBlockState(pos.offset(direction.getOpposite()), config.fillerState, 2);

            if (rand.nextFloat() <= config.featureChance * 1.5F) {
                if (rand.nextFloat() <= 0.75F) generateColumn(world, rand, pos.up());
                else world.setBlockState(pos.up(), HallowsBlocks.IGNIS_CRYSTAL_FLOWER.get().getDefaultState().with(IgnisCrystalBlock.HALF, DoubleBlockHalf.UPPER), 2);
            } else if (rand.nextFloat() <= config.featureChance) {
                for (BlockPos p : BlockPos.getRandomPositions(rand, rand.nextInt(11) + 6, pos.getX() - 2, pos.getY(), pos.getZ() - 2, pos.getX() + 2, pos.getY() + 1, pos.getZ() + 2)) {
                    if (world.isAirBlock(p.up()) && !world.isAirBlock(p.down())) {
                        AtomicBoolean flag = new AtomicBoolean(true);
                        Direction.Plane.HORIZONTAL.getDirectionValues().forEach((d) -> {
                            if (world.isAirBlock(p.offset(d))) flag.set(false);
                        });

                        if (flag.get()) world.setBlockState(p, Blocks.LAVA.getDefaultState(), 2);
                    }
                }
            }
        }
    }

    private void generateColumn(ISeedReader reader, Random rand, BlockPos pos) {
        int height = rand.nextInt(rand.nextInt(6) + 6) + 2;

        BlockPos.Mutable place = pos.toMutable();
        int maxHeight = place.getY() + height;

        while (place.getY() <= maxHeight) {
            if (!reader.isAirBlock(place.up())) maxHeight = place.getY();

            reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_BLOCK.get().getDefaultState(), 2);
            place.move(Direction.UP);
        }
    }
}