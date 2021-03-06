package pugz.hallows.common.world.decorator;

import com.minecraftabnormals.abnormals_core.common.blocks.wood.WoodPostBlock;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import pugz.hallows.core.registry.HallowsFeatures;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class BranchTreeDecorator extends TreeDecorator {
    public static final Codec<BranchTreeDecorator> CODEC = RecordCodecBuilder.create((p_236568_0_) -> {
        return p_236568_0_.group(BlockState.CODEC.fieldOf("state").forGetter((config) -> {
            return config.state;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> {
            return config.probability;
        }), Codec.INT.fieldOf("maxLength").forGetter((config) -> {
            return config.maxLength;
        }), Codec.floatRange(0.0F, 1.0F).fieldOf("branchDensity").forGetter((config) -> {
            return config.branchDensity;
        })).apply(p_236568_0_, BranchTreeDecorator::new);
    });
    private final BlockState state;
    private final float probability;
    private final int maxLength;
    private final float branchDensity;

    public BranchTreeDecorator(BlockState state, float probabilityIn, int maxLength, float branchDensity) {
        this.state = state;
        this.probability = probabilityIn;
        this.maxLength = maxLength;
        this.branchDensity = branchDensity;
    }

    @Nonnull
    protected TreeDecoratorType<?> func_230380_a_() {
        return HallowsFeatures.Decorators.BRANCH.get();
    }

    public void func_225576_a_(ISeedReader world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox) {
        if (!(random.nextFloat() >= this.probability) && state.getBlock() instanceof WoodPostBlock) {
            for (BlockPos pos : logPositions) {
                BlockPos.Mutable blockpos$mutable = pos.toMutable();
                Direction direction = Direction.Plane.HORIZONTAL.random(random);
                for (int length = random.nextInt(maxLength) + 1; length > 0; --length) {
                    blockpos$mutable.setAndMove(blockpos$mutable, direction);
                    if (world.isAirBlock(blockpos$mutable) && world.isAirBlock(blockpos$mutable.down()) && random.nextFloat() <= branchDensity) {
                        world.setBlockState(blockpos$mutable, this.state.with(BlockStateProperties.AXIS, direction.getAxis()), 19);
                        changedBlocks.add(blockpos$mutable);
                        boundingBox.expandTo(new MutableBoundingBox(blockpos$mutable, blockpos$mutable));
                    } else break;
                }
            }
        }
    }
}