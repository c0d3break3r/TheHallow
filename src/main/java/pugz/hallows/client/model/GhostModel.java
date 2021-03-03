package pugz.hallows.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import pugz.hallows.common.entity.GhostEntity;

public class GhostModel extends EntityModel<GhostEntity> {
	private final ModelRenderer body;
	private final ModelRenderer leftArm;
	private final ModelRenderer rightArm;
	private final ModelRenderer head;

	public GhostModel() {
		textureWidth = 64;
		textureHeight = 64;

		body = new ModelRenderer(this);
		body.setRotationPoint(0.0F, 14.0F, -1.0F);
		body.setTextureOffset(0, 17).addBox(-4.0F, -2.0F, -2.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);
		body.setTextureOffset(26, 0).addBox(-4.0F, 8.0F, -2.0F, 7.0F, 2.0F, 6.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-4.0F, 0.5F, 0.5F);
		body.addChild(leftArm);
		leftArm.setTextureOffset(7, 20).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(3.0F, 0.5F, 0.5F);
		body.addChild(rightArm);
		rightArm.setTextureOffset(7, 20).addBox(0.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(0.0F, 12.0F, -1.0F);
		setRotationAngle(head, 0.0F, 3.1416F, 0.0F);
		head.setTextureOffset(0, 0).addBox(-4.5F, -9.0F, -2.0F, 9.0F, 9.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(18, 25).addBox(-3.5F, 0.0F, -2.0F, 7.0F, 4.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(GhostEntity ghost, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		ModelHelper.func_239105_a_(this.leftArm, this.rightArm, true, this.swingProgress, ageInTicks);

		if (ghost.isCharging()) {
			for (float i = 0; i <= 40; i += 5F) {
				this.head.rotateAngleX = i;
				this.rightArm.rotateAngleX = i;
				this.leftArm.rotateAngleX = i;
			}
			for (float i = 0; i <= 20.0F; i += 2.5F) {
				this.body.rotateAngleX = -i;
				this.rightArm.rotateAngleZ = -i;
				this.leftArm.rotateAngleZ = i;
			}
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}

	public void setRotationAngle(ModelRenderer modelRenderer, float x, float y, float z) {
		modelRenderer.rotateAngleX = x;
		modelRenderer.rotateAngleY = y;
		modelRenderer.rotateAngleZ = z;
	}
}