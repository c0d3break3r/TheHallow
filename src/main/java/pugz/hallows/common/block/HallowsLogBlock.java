package pugz.hallows.common.block;

import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.AxeItem;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;

public class HallowsLogBlock extends RotatedPillarBlock {
    private final RotatedPillarBlock stripped;

    public HallowsLogBlock(RotatedPillarBlock stripped) {
        super(Properties.from(Blocks.OAK_LOG));
        this.stripped = stripped;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getHeldItem(handIn);

        if (held.getItem() instanceof AxeItem) {
            worldIn.setBlockState(pos, stripped.getDefaultState().with(RotatedPillarBlock.AXIS, state.get(RotatedPillarBlock.AXIS)), 2);

            held.damageItem(1, player, (p) -> {
                p.sendBreakAnimation(handIn);
            });

            return ActionResultType.func_233537_a_(worldIn.isRemote);
        }

        return ActionResultType.PASS;
    }
}