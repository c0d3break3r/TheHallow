package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.server.ServerWorld;

public class HallowsOreBlock extends Block {
    private final int minExperience;
    private final int maxExperience;

    public HallowsOreBlock(AbstractBlock.Properties properties, int minExperience, int maxExperience) {
        super(properties);
        this.minExperience = minExperience;
        this.maxExperience = maxExperience;
    }

    @SuppressWarnings("deprecation")
    public void spawnAdditionalDrops(BlockState state, ServerWorld worldIn, BlockPos pos, ItemStack stack) {
        super.spawnAdditionalDrops(state, worldIn, pos, stack);
    }

    @Override
    public int getExpDrop(BlockState state, IWorldReader reader, BlockPos pos, int fortune, int silktouch) {
        return silktouch == 0 ? MathHelper.nextInt(RANDOM, minExperience, maxExperience) : 0;
    }
}