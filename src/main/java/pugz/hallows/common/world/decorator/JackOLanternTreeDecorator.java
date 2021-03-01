package pugz.hallows.common.world.decorator;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.Util;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import pugz.hallows.core.registry.HallowsFeatures;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

public class JackOLanternTreeDecorator extends TreeDecorator {
    public static final Codec<JackOLanternTreeDecorator> CODEC = Codec.floatRange(0.0F, 1.0F).fieldOf("probability").xmap(JackOLanternTreeDecorator::new, (p_236865_0_) -> {
        return p_236865_0_.probability;
    }).codec();
    private final float probability;

    public JackOLanternTreeDecorator(float probabilityIn) {
        this.probability = probabilityIn;
    }

    @Nonnull
    protected TreeDecoratorType<?> func_230380_a_() {
        return HallowsFeatures.Decorators.JACK_O_LANTERN.get();
    }

    public void func_225576_a_(ISeedReader reader, Random random, List<BlockPos> logPositions, List<BlockPos> leafPositions, Set<BlockPos> p_225576_5_, MutableBoundingBox boundingBox) {
        if (!(random.nextFloat() >= this.probability)) {
            Direction direction = Util.getRandomObject(new Direction[]{Direction.WEST, Direction.EAST, Direction.SOUTH}, random);
            int i = !leafPositions.isEmpty() ? Math.max(leafPositions.get(0).getY() - 1, logPositions.get(0).getY()) : Math.min(logPositions.get(0).getY() + 1 + random.nextInt(3), logPositions.get(logPositions.size() - 1).getY());
            List<BlockPos> list = logPositions.stream().filter((pos) -> {
                return pos.getY() == i;
            }).collect(Collectors.toList());
            if (!list.isEmpty()) {
                BlockPos blockpos = list.get(random.nextInt(list.size()));
                BlockPos blockpos1 = blockpos.offset(direction);
                if (Feature.isAirAt(reader, blockpos1) && Feature.isAirAt(reader, blockpos1.offset(Direction.SOUTH))) {
                    BlockState blockstate = Blocks.JACK_O_LANTERN.getDefaultState().with(CarvedPumpkinBlock.FACING, Direction.SOUTH);
                    this.func_227423_a_(reader, blockpos1, blockstate, p_225576_5_, boundingBox);
                }
            }
        }
    }
}