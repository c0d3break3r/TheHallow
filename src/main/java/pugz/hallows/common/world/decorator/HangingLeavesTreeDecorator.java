package pugz.hallows.common.world.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import pugz.hallows.common.block.HangingLeavesBlock;
import pugz.hallows.core.registry.HallowsFeatures;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class HangingLeavesTreeDecorator extends TreeDecorator {
    public static final Codec<HangingLeavesTreeDecorator> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> {
            return config.probability;
        })).apply(p_236568_0_, HangingLeavesTreeDecorator::new);
    });
    private final BlockState state;
    private final float probability;

    public HangingLeavesTreeDecorator(BlockState state, float probabilityIn) {
        this.state = state;
        this.probability = probabilityIn;
    }

    @Nonnull
    protected TreeDecoratorType<?> func_230380_a_() {
        return HallowsFeatures.Decorators.HANGING_LEAVES.get();
    }

    public void func_225576_a_(ISeedReader world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox) {
        if (!(random.nextFloat() >= this.probability)) {
            for (BlockPos pos : leavesPositions) {
                if (world.isAirBlock(pos.down()) && state.getBlock() instanceof HangingLeavesBlock) {
                    BlockState blockstate = this.state.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.LARGE);
                    int length = random.nextInt(3) + 2;
                    for (int j = pos.down().getY(); j >= pos.down().getY() - length; --j) {
                        if (j == pos.down().getY() - length) blockstate = blockstate.with(HangingLeavesBlock.HALF, HangingLeavesBlock.Half.SMALL);
                        if (blockstate.isValidPosition(world, pos)) {
                            BlockPos place = new BlockPos(pos.getX(), j, pos.getZ());
                            if (world.isAirBlock(place)) {
                                world.setBlockState(place, blockstate, 19);
                                changedBlocks.add(place);
                                boundingBox.expandTo(new MutableBoundingBox(place, place));
                            }
                        }
                    }
                }
            }
        }
    }
}