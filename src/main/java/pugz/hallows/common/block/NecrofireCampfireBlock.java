package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.CampfireBlock;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.common.tileentity.NecrofireCampfireTileEntity;

import java.util.Random;

public class NecrofireCampfireBlock extends CampfireBlock {
    public NecrofireCampfireBlock(AbstractBlock.Properties properties) {
        super(false, 3, properties);
    }

    @Override
    public TileEntity newBlockEntity(IBlockReader p_196283_1_) {
        return new NecrofireCampfireTileEntity();
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (stateIn.getValue(LIT)) {
            if (rand.nextInt(10) == 0) worldIn.playLocalSound((float) pos.getX() + 0.5F, (float) pos.getY() + 0.5F, (float) pos.getZ() + 0.5F, SoundEvents.CAMPFIRE_CRACKLE, SoundCategory.BLOCKS, 0.5F + rand.nextFloat(), rand.nextFloat() * 0.7F + 0.6F, false);
        }
    }
}