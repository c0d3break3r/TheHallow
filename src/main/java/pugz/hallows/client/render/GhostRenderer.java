package pugz.hallows.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.client.model.GhostModel;
import pugz.hallows.common.entity.GhostEntity;
import pugz.hallows.core.Hallows;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class GhostRenderer extends MobRenderer<GhostEntity, GhostModel> {
    public GhostRenderer(EntityRendererManager manager) {
        super(manager, new GhostModel(), 0.9F);
    }

    protected void scale(GhostEntity ghost, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.2F, 1.2F, 1.2F);
    }

    @Nonnull
    public Vector3d getRenderOffset(GhostEntity ghost, float partialTicks) {
        if (ghost.isCharging()) {
            return new Vector3d(ghost.getRandom().nextGaussian() * 0.02D, 0.0D, ghost.getRandom().nextGaussian() * 0.02D);
        } else {
            return super.getRenderOffset(ghost, partialTicks);
        }
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(GhostEntity ghost) {
        return new ResourceLocation(Hallows.MOD_ID, "textures/entity/ghost.png");
    }
}