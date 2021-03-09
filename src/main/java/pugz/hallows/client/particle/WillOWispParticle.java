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
        this.particleScale = 0.25F + (float)Math.random() + (float)Math.random();
        this.setAlphaF(0.1F + (float)Math.random());
        this.maxAge = (int)(8.0D / (Math.random() * 0.8D + 0.25D)) + 6;
        this.canCollide = false;
        this.motionX = motionX + (Math.random() / 2.0F);
        this.motionY = motionY + (Math.random() / 2.0F);
        this.motionZ = motionZ + (Math.random() / 2.0F);
    }

    @Nonnull
    @Override
    public IParticleRenderType getRenderType() {
        return IParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public void tick() {
        if (this.age++ >= this.maxAge) {
            this.setExpired();
        } else {
            this.setAlphaF(this.particleAlpha - (this.particleAlpha / this.maxAge));
            this.move(this.motionX, this.motionY, this.motionZ);
        }
    }

    public int getBrightnessForRender(float partialTick) {
        float f = ((float)this.age + partialTick) / (float)this.maxAge;
        f = MathHelper.clamp(f, 0.0F, 1.0F);
        int i = super.getBrightnessForRender(partialTick);
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

        public Particle makeParticle(BasicParticleType typeIn, ClientWorld worldIn, double x, double y, double z, double xSpeed, double ySpeed, double zSpeed) {
            WillOWispParticle willowispparticle = new WillOWispParticle(worldIn, x, y, z, xSpeed, ySpeed, zSpeed);
            willowispparticle.selectSpriteRandomly(this.spriteSet);
            return willowispparticle;
        }
    }
}