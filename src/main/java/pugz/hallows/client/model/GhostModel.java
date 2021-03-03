package pugz.hallows.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelHelper;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
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
		head.rotateAngleX = 0.0F;
		head.rotateAngleY = 3.1416F;
		head.rotateAngleZ = 0.0F;
		head.setTextureOffset(0, 0).addBox(-4.0F, -9.0F, -2.0F, 9.0F, 9.0F, 8.0F, 0.0F, false);
		head.setTextureOffset(18, 25).addBox(-3.0F, 0.0F, -2.0F, 7.0F, 4.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(GhostEntity ghost, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		ModelHelper.func_239105_a_(this.leftArm, this.rightArm, true, this.swingProgress, ageInTicks);
		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.5F;
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.5F;
		this.rightArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.25F;
		this.leftArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.25F;

		if (ghost.isCharging()) {
			this.rightArm.rotateAngleX = 37.5F + MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.375F;
			this.leftArm.rotateAngleX = 37.5F + MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.375F;
			this.rightArm.rotateAngleZ = 17.5F + MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.375F;
			this.leftArm.rotateAngleZ = -17.5F - MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.375F;
		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}