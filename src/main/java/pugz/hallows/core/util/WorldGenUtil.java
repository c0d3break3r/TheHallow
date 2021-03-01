package pugz.hallows.core.util;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.feature.template.RuleTest;

public class WorldGenUtil {
    public static BlockPos.Mutable getFloorPosition(ISeedReader world, BlockPos pos, RuleTest target, boolean forCeiling) {
        BlockPos.Mutable pos$mutable = pos.toMutable();

        for (int y = 0; y <= 128; ++y) {
            pos$mutable.setY(y);

            Block block = world.getBlockState(pos$mutable).getBlock();
            Block up = world.getBlockState(pos$mutable.up()).getBlock();

            if ((block == Blocks.CAVE_AIR ||
                    block == Blocks.WATER) &&
                    up.getBlock() != Blocks.AIR &&
                    (target.test(world.getBlockState(pos$mutable), world.getRandom()) || (forCeiling && world.getBlockState(pos.down()).getBlock() == Blocks.LAVA)) &&
                    !world.canBlockSeeSky(pos$mutable)) break;
        }

        return pos$mutable;
    }
}