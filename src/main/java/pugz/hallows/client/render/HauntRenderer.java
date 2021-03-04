package pugz.hallows.client.render;

import com.mojang.blaze3d.matrix.MatrixStack;
import net.minecraft.client.renderer.entity.EntityRendererManager;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import pugz.hallows.client.model.HauntModel;
import pugz.hallows.common.entity.HauntEntity;
import pugz.hallows.core.Hallows;

import javax.annotation.Nonnull;

@OnlyIn(Dist.CLIENT)
public class HauntRenderer extends MobRenderer<HauntEntity, HauntModel> {
    public HauntRenderer(EntityRendererManager manager) {
        super(manager, new HauntModel(), 0.9F);
    }

    protected void preRenderCallback(HauntEntity haunt, MatrixStack matrixStackIn, float partialTickTime) {
        matrixStackIn.scale(1.2F, 1.2F, 1.2F);
    }

    @Nonnull
    @Override
    public ResourceLocation getEntityTexture(HauntEntity haunt) {
        return new ResourceLocation(Hallows.MOD_ID, "textures/entity/haunt.png");
    }
}