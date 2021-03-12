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
        super(Properties.copy(Blocks.OAK_LOG));
        this.stripped = stripped;
    }

    @Nonnull
    @Override
    @SuppressWarnings("deprecation")
    public ActionResultType use(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        ItemStack held = player.getItemInHand(handIn);

        if (held.getItem() instanceof AxeItem) {
            worldIn.setBlock(pos, stripped.defaultBlockState().setValue(RotatedPillarBlock.AXIS, state.getValue(RotatedPillarBlock.AXIS)), 2);

            held.hurtAndBreak(1, player, (p) -> {
                p.broadcastBreakEvent(handIn);
            });

            return ActionResultType.sidedSuccess(worldIn.isClientSide);
        }

        return ActionResultType.PASS;
    }
}