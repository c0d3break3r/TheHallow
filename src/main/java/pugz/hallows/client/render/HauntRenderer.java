package pugz.hallows.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.client.model.HauntModel;
import pugz.hallows.common.entity.HauntEntity;
import pugz.hallows.core.Hallows;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class HauntRenderer extends MobRenderer<HauntEntity, EntityModel<HauntEntity>> {
    public HauntRenderer(EntityRendererManager manager) {
        super(manager, new HauntModel<>(), 0.8F);
    }

    protected void scale(HauntEntity haunt, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.2F, 1.2F, 1.2F);
    }

    @Nonnull
    public Vector3d getRenderOffset(HauntEntity haunt, float partialTicks) {
        if (haunt.isScreaming()) {
            return new Vector3d(haunt.getRandom().nextGaussian() * 0.01D, 0.0D, haunt.getRandom().nextGaussian() * 0.01D);
        } else {
            return super.getRenderOffset(haunt, partialTicks);
        }
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(HauntEntity haunt) {
        return new ResourceLocation(Hallows.MOD_ID, "textures/entity/haunt.png");
    }
}