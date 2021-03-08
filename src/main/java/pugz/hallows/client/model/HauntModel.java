package pugz.hallows.client.model;

import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.client.renderer.entity.model.EntityModel;
import net.minecraft.client.renderer.model.ModelRenderer;
import net.minecraft.util.math.MathHelper;
import pugz.hallows.common.entity.HauntEntity;

public class HauntModel extends EntityModel<HauntEntity> {
	private final ModelRenderer rightLeg;
	private final ModelRenderer leftLeg;
	private final ModelRenderer body;
	private final ModelRenderer body_r1;
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public HauntModel() {
		textureWidth = 128;
		textureHeight = 128;

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightLeg.setTextureOffset(16, 48).addBox(-6.5F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(7.0F, 12.0F, 2.0F);
		leftLeg.setTextureOffset(16, 48).addBox(-4.5F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(9.0F, 24.0F, 0.0F);


		body_r1 = new ModelRenderer(this);
		body_r1.setRotationPoint(-9.0F, 0.0F, 4.0F);
		body.addChild(body_r1);
		body_r1.setTextureOffset(0, 0).addBox(-9.5F, -30.0F, -3.0F, 18.0F, 18.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(-9.0F, -27.0F, -6.0F);
		body.addChild(head);
		head.setTextureOffset(0, 28).addBox(-5.0F, -10.0F, -4.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(30, 28).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 6.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-8.5F, -2.0F, 2.0F);
		rightArm.setTextureOffset(0, 48).addBox(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(13.5F, -2.0F, 2.0F);
		leftArm.setTextureOffset(0, 48).addBox(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(HauntEntity haunt, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		body_r1.rotateAngleX = 0.2618F;

		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * limbSwingAmount * 0.25F;
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F) * limbSwingAmount * 0.25F;
		this.rightArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * limbSwingAmount * 0.2F;
		this.leftArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.1681F) * limbSwingAmount * 0.2F;

		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F) * 0.3F * limbSwingAmount;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * 0.3F * limbSwingAmount;

		if (haunt.isScreaming()) {

		}
	}

	@Override
	public void render(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		rightLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		leftLeg.render(matrixStack, buffer, packedLight, packedOverlay);
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		rightArm.render(matrixStack, buffer, packedLight, packedOverlay);
		leftArm.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}