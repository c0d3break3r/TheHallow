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
		texWidth = 64;
		texHeight = 64;

		body = new ModelRenderer(this);
		body.setPos(0.0F, 14.0F, -1.0F);
		body.texOffs(0, 17).addBox(-4.0F, -2.0F, -2.0F, 7.0F, 10.0F, 6.0F, 0.0F, false);
		body.texOffs(26, 0).addBox(-4.0F, 8.0F, -2.0F, 7.0F, 2.0F, 6.0F, 0.0F, false);

		leftArm = new ModelRenderer(this);
		leftArm.setPos(-4.0F, 0.5F, 0.5F);
		body.addChild(leftArm);
		leftArm.texOffs(7, 20).addBox(-3.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		rightArm = new ModelRenderer(this);
		rightArm.setPos(3.0F, 0.5F, 0.5F);
		body.addChild(rightArm);
		rightArm.texOffs(7, 20).addBox(0.0F, -1.5F, -1.5F, 3.0F, 8.0F, 3.0F, 0.0F, false);

		head = new ModelRenderer(this);
		head.setPos(0.0F, 12.0F, -1.0F);
		head.xRot = 0.0F;
		head.yRot = 3.1416F;
		head.zRot = 0.0F;
		head.texOffs(0, 0).addBox(-4.0F, -9.0F, -2.0F, 9.0F, 9.0F, 8.0F, 0.0F, false);
		head.texOffs(18, 25).addBox(-3.0F, 0.0F, -2.0F, 7.0F, 4.0F, 8.0F, 0.0F, false);
	}

	@Override
	public void setupAnim(GhostEntity ghost, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		ModelHelper.animateZombieArms(this.leftArm, this.rightArm, true, this.attackTime, ageInTicks);

		this.rightArm.xRot = MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.5F;
		this.leftArm.xRot = MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.5F;
		this.rightArm.zRot = MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.25F;
		this.leftArm.zRot = MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.25F;

		if (ghost.isCharging()) {
			this.rightArm.xRot = 37.5F + MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.375F;
			this.leftArm.xRot = 37.5F + MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.375F;
			this.rightArm.zRot = 17.5F + MathHelper.cos(limbSwing * 0.3331F + (float)Math.PI) * limbSwingAmount * 0.375F;
			this.leftArm.zRot = -17.5F - MathHelper.cos(limbSwing * 0.3331F) * limbSwingAmount * 0.375F;
		}
	}

	@Override
	public void renderToBuffer(MatrixStack matrixStack, IVertexBuilder buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha){
		body.render(matrixStack, buffer, packedLight, packedOverlay);
		head.render(matrixStack, buffer, packedLight, packedOverlay);
	}
}