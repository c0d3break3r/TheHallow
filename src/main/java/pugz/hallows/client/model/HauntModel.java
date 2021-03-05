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
	private final ModelRenderer head;
	private final ModelRenderer rightArm;
	private final ModelRenderer leftArm;

	public HauntModel() {
		textureWidth = 16;
		textureHeight = 16;

		rightLeg = new ModelRenderer(this);
		rightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightLeg.setTextureOffset(0, 0).addBox(-4.0F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		leftLeg = new ModelRenderer(this);
		leftLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		leftLeg.setTextureOffset(0, 0).addBox(5.0F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		body = new ModelRenderer(this);
		body.setRotationPoint(9.0F, 24.0F, 0.0F);
		body.setTextureOffset(0, 0).addBox(-15.5F, -30.0F, -3.0F, 18.0F, 18.0F, 10.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setRotationPoint(-6.5F, -27.0F, -3.0F);
		body.addChild(head);
		head.setTextureOffset(0, 0).addBox(-5.0F, -10.0F, -4.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(0, 0).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 6.0F, 4.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setRotationPoint(-6.5F, 8.0F, 0.0F);
		rightArm.setTextureOffset(0, 0).addBox(-4.0F, -12.0F, 0.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setRotationPoint(-6.5F, 8.0F, 0.0F);
		leftArm.setTextureOffset(0, 0).addBox(18.0F, -12.0F, 0.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
	}

	public void setRotationAngles(HauntEntity haunt, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.head.rotateAngleY = netHeadYaw * ((float)Math.PI / 180F);
		this.head.rotateAngleX = headPitch * ((float)Math.PI / 180F);
		this.leftLeg.rotateAngleX = -1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.rightLeg.rotateAngleX = 1.5F * MathHelper.func_233021_e_(limbSwing, 13.0F) * limbSwingAmount;
		this.leftLeg.rotateAngleY = 0.0F;
		this.rightLeg.rotateAngleY = 0.0F;

		if (haunt.isScreaming()) {
			this.head.rotateAngleZ = MathHelper.cos(ageInTicks * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.5F;
		}
	}

	public void setLivingAnimations(HauntEntity haunt, float limbSwing, float limbSwingAmount, float partialTick) {
		if (haunt.getAttackTimer() > 0) {
			this.rightArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)haunt.getAttackTimer() - partialTick, 10.0F);
			this.leftArm.rotateAngleX = -2.0F + 1.5F * MathHelper.func_233021_e_((float)haunt.getAttackTimer() - partialTick, 10.0F);
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