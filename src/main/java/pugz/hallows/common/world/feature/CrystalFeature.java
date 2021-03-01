package pugz.hallows.common.world.feature;

import net.minecraft.block.BlockState;
import net.minecraft.state.properties.DoubleBlockHalf;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.template.TagMatchRuleTest;
import pugz.hallows.common.block.IgnisCrystalBlock;
import pugz.hallows.common.block.IgnisCrystalFlowerBlock;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.other.HallowsTags;
import pugz.hallows.core.util.WorldGenUtil;

import java.util.Random;

public class CrystalFeature extends Feature<NoFeatureConfig> {
    public CrystalFeature() {
        super(NoFeatureConfig.field_236558_a_);
    }

    @Override
    public boolean generate(ISeedReader reader, ChunkGenerator generator, Random rand, BlockPos center, NoFeatureConfig config) {
        int columns = rand.nextInt(3) + 2;
        int spreadX = (rand.nextInt(4) + (columns / 2));
        int spreadZ = (rand.nextInt(4) + (columns / 2));

        for (BlockPos pos : BlockPos.getRandomPositions(rand, columns, center.getX() - spreadX, center.getY(), center.getZ() - spreadZ, center.getX() + spreadX, center.getY(), center.getZ() + spreadZ)) {
            BlockState down = reader.getBlockState(pos.down());
            if (down.isIn(HallowsBlocks.TENEBRITE.get()) || down.isIn(HallowsBlocks.GILDED_TENEBRITE.get())) generateColumn(reader, rand, pos, center);
        }
        return true;
    }

    public static void generateColumn(ISeedReader reader, Random rand, BlockPos pos, BlockPos center) {
        int height = rand.nextInt(rand.nextInt(3) + 8 - (int)(Math.hypot(pos.getX() - center.getX(), pos.getZ() - center.getZ()))) + 1;

        BlockPos.Mutable place = WorldGenUtil.getFloorPosition(reader, pos, new TagMatchRuleTest(HallowsTags.Blocks.BASE_STONE_HALLOWS), false).down().toMutable();
        int maxHeight = pos.getY() + height;

        while (place.getY() <= maxHeight) {
            if (reader.getBlockState(place).isAir()) {
                boolean waterlogged = reader.getFluidState(place).isTagged(FluidTags.WATER);

                if (place.getY() <= maxHeight * 0.5F) {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_BLOCK.get().getDefaultState(), 3);
                    continue;
                } else if (place.getY() <= maxHeight * 0.75F) {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_STEM.get().getDefaultState().with(IgnisCrystalBlock.WATERLOGGED, waterlogged), 3);
                    continue;
                } else {
                    reader.setBlockState(place, HallowsBlocks.IGNIS_CRYSTAL_FLOWER.get().getDefaultState().with(IgnisCrystalFlowerBlock.HALF, place.getY() < pos.getY() + height ? DoubleBlockHalf.LOWER : DoubleBlockHalf.UPPER).with(IgnisCrystalFlowerBlock.WATERLOGGED, waterlogged), 3);
                    continue;
                }
            }

            place.move(Direction.UP);
        }
    }
}