package pugz.hallows.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
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

    protected void preRenderCallback(GhostEntity ghost, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.5F, 1.5F, 1.5F);
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(GhostEntity entity) {
        return new ResourceLocation(Hallows.MOD_ID, "textures/entity/ghost.png");
    }
}