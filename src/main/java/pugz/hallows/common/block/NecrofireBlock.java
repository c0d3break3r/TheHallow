package pugz.hallows.common.block;

import net.minecraft.advancements.CriteriaTriggers;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.other.HallowsTags;

import javax.annotation.Nonnull;

public class NecrofireBlock extends AbstractFireBlock {
    public NecrofireBlock(AbstractBlock.Properties properties) {
        super(properties, 1.5F);
    }

    @Nonnull
    @SuppressWarnings("deprecation")
    public BlockState updatePostPlacement(BlockState stateIn, Direction facing, BlockState facingState, IWorld worldIn, BlockPos currentPos, BlockPos facingPos) {
        return this.isValidPosition(stateIn, worldIn, currentPos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
    }

    @SuppressWarnings("deprecation")
    public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return shouldLightNecrofire(worldIn.getBlockState(pos.down()).getBlock());
    }

    public static boolean shouldLightNecrofire(Block block) {
        return block.isIn(HallowsTags.Blocks.NECROFIRE_BASE_BLOCKS);
    }

    protected boolean canBurn(BlockState state) {
        return true;
    }

    @SuppressWarnings("deprecation")
    public static void onRightClickBlock(PlayerInteractEvent.RightClickBlock event) {
        World world = event.getWorld();
        ItemStack stack = event.getItemStack();
        BlockPos pos = event.getPos();
        BlockState state = world.getBlockState(pos);

        if (stack.getItem() == Items.FLINT_AND_STEEL) {
            BlockPos place = pos.offset(event.getFace());
            if (AbstractFireBlock.canLightBlock(world, place, event.getFace()) && shouldLightNecrofire(state.getBlock())) {
                PlayerEntity player = event.getPlayer();
                world.playSound(player, place, SoundEvents.ITEM_FLINTANDSTEEL_USE, SoundCategory.BLOCKS, 1.0F, world.getRandom().nextFloat() * 0.4F + 0.8F);
                if (world.getBlockState(place).isAir()) world.setBlockState(place, HallowsBlocks.NECROFIRE.get().getDefaultState(), 11);

                if (player instanceof ServerPlayerEntity) {
                    CriteriaTriggers.PLACED_BLOCK.trigger((ServerPlayerEntity) player, place, stack);
                    stack.damageItem(1, player, (p) -> {
                        p.sendBreakAnimation(event.getHand());
                    });
                }

                event.setCancellationResult(ActionResultType.func_233537_a_(world.isRemote()));
                event.setCanceled(true);
            }
        }
    }
}