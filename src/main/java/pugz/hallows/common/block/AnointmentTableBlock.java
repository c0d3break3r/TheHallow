package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CraftingTableBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.container.INamedContainerProvider;
import net.minecraft.inventory.container.SimpleNamedContainerProvider;
import net.minecraft.util.ActionResultType;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import net.minecraft.world.World;
import pugz.hallows.common.tileentity.container.AnointingTableContainer;

import javax.annotation.Nonnull;

public class AnointmentTableBlock extends CraftingTableBlock {
    private static final ITextComponent CONTAINER_NAME = new TranslationTextComponent("container.anoint");

    public AnointmentTableBlock(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Override
    public INamedContainerProvider getMenuProvider(BlockState state, World world, BlockPos pos) {
        return new SimpleNamedContainerProvider((id, inventory, player) -> {
            return new AnointingTableContainer(id, inventory);
        }, CONTAINER_NAME);
    }

    @Nonnull
    public ActionResultType onBlockActivated(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockRayTraceResult hit) {
        if (worldIn.isClientSide) {
            return ActionResultType.SUCCESS;
        } else {
            player.openMenu(state.getMenuProvider(worldIn, pos));
            return ActionResultType.CONSUME;
        }
    }
}
