package pugz.hallows.common.block;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.core.registry.HallowsParticles;

import java.util.Random;

public class NecrofireWallTorchBlock extends WallTorchBlock {
    public NecrofireWallTorchBlock(AbstractBlock.Properties properties) {
        super(properties, ParticleTypes.FLAME);
    }

    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        Direction direction = stateIn.get(HORIZONTAL_FACING);
        double d0 = (double) pos.getX() + 0.5D;
        double d1 = (double) pos.getY() + 0.7D;
        double d2 = (double) pos.getZ() + 0.5D;
        Direction direction1 = direction.getOpposite();
        worldIn.addParticle(ParticleTypes.SMOKE, d0 + 0.27D * (double) direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double) direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
        worldIn.addParticle(HallowsParticles.NECROFLAME.get(), d0 + 0.27D * (double) direction1.getXOffset(), d1 + 0.22D, d2 + 0.27D * (double) direction1.getZOffset(), 0.0D, 0.0D, 0.0D);
    }
}