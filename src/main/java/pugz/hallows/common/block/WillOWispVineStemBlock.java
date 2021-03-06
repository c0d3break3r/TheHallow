package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.util.Direction;
import net.minecraft.util.math.shapes.VoxelShape;
import pugz.hallows.core.registry.HallowsBlocks;

import javax.annotation.Nonnull;

public class WillOWispVineStemBlock extends AbstractBodyPlantBlock {
    public static final VoxelShape SHAPE = Block.makeCuboidShape(1.0D, 0.0D, 1.0D, 15.0D, 16.0D, 15.0D);

    public WillOWispVineStemBlock(AbstractBlock.Properties properties) {
        super(properties, Direction.DOWN, SHAPE, false);
    }

    @Nonnull
    protected AbstractTopPlantBlock getTopPlantBlock() {
        return (AbstractTopPlantBlock) HallowsBlocks.WILL_O_WISP_FRUIT.get();
    }
}