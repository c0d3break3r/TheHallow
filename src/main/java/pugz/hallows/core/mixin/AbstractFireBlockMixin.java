package pugz.hallows.core.mixin;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import pugz.hallows.common.block.NecrofireBlock;
import pugz.hallows.core.registry.HallowsBlocks;

@Mixin(AbstractFireBlock.class)
public final class AbstractFireBlockMixin extends Block {
    private AbstractFireBlockMixin(AbstractBlock.Properties properties) {
        super(properties);
    }

    @Inject(at = @At("HEAD"), method = "getState(Lnet/minecraft/world/IBlockReader;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;", cancellable = true)
    private static void necrofirePlacement(IBlockReader reader, BlockPos pos, CallbackInfoReturnable<BlockState> info) {
        if (NecrofireBlock.isNecrofireBase(reader.getBlockState(pos.below()).getBlock())) {
            info.setReturnValue(HallowsBlocks.NECROFIRE.get().defaultBlockState());
        }
    }
}