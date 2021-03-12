package pugz.hallows.client.particle;

import net.minecraft.client.particle.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particles.BasicParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class WillOWispParticle extends SpriteTexturedParticle {
    private WillOWispParticle(ClientWorld world, double x, double y, double z, double motionX, double motionY, double motionZ) {
        super(world, x, y, z, motionX, motionY, motionZ);
        this.scale(0.25F + (float)Math.random() + (float)Math.random());
        this.setAlpha(0.1F + (float)Math.random());
        this.age = (int)(8.0D / (Math.random() * 0.8D + 0.25D)) + 6;
        this.hasPhysics = false;
        this.xd = motionX + (Math.random() / 2.0F);
        this.yd = motionY + (Math.random() / 2.0F);
        this.zd = motionZ + (Math.random() / 2.0F);
    }

    @Nonnull
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        if (this.age++ >= this.age) {
            this.remove();
        } else {
            this.setAlpha(this.alpha - (this.alpha / this.age));
            this.move(this.xd, this.yd, this.zd);
        }
    }

    public int getLightColor(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.age;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getLightColor(partialTick);
        int j = i & 255;
        int k = i >> 16 & 255;
        j = j + (int)(f * 15.0F * 16.0F);
        if (j > 240) {
            j = 240;
        }

        return j | k << 16;
    }

    @OnlyIn(Dist.CLIENT)
    public static class Factory implements IParticleFactory<BasicParticleType> {
        private final IAnimatedSprite spriteSet;

        public Factory(IAnimatedSprite spriteSet) {
            this.spriteSet = spriteSet;
        }

        public Particle createParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            WillOWispParticle willowispparticle = new WillOWispParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            willowispparticle.pickSprite(this.spriteSet);
            return willowispparticle;
        }
    }
}