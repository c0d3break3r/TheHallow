package pugz.hallows.client.model;

import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatorEntityModel;
import com.minecraftabnormals.abnormals_core.core.endimator.entity.EndimatorModelRenderer;
import com.mojang.blaze3d.matrix.MatrixStack;
import com.mojang.blaze3d.vertex.IVertexBuilder;
import net.minecraft.util.math.MathHelper;
import pugz.hallows.common.entity.HauntEntity;

public class HauntModel<E extends HauntEntity> extends EndimatorEntityModel<E> {
	private final EndimatorModelRenderer rightLeg;
	private final EndimatorModelRenderer leftLeg;
	private final EndimatorModelRenderer body;
	private final EndimatorModelRenderer body_r1;
	private final EndimatorModelRenderer head;
	private final EndimatorModelRenderer rightArm;
	private final EndimatorModelRenderer leftArm;

	public HauntModel() {
		textureWidth = 128;
		textureHeight = 128;

		rightLeg = new EndimatorModelRenderer(this, 16, 48);
		rightLeg.setRotationPoint(0.0F, 24.0F, 0.0F);
		rightLeg.addBox(-6.5F, -14.0F, 0.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		leftLeg = new EndimatorModelRenderer(this, 16, 48);
		leftLeg.setRotationPoint(7.0F, 12.0F, 2.0F);
		leftLeg.addBox(-4.5F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, 0.0F, false);

		body = new EndimatorModelRenderer(this, 0, 0);
		body.setRotationPoint(9.0F, 24.0F, 0.0F);

		body_r1 = new EndimatorModelRenderer(this, 0, 0);
		body_r1.setRotationPoint(-9.0F, 0.0F, 4.0F);
		body.addChild(body_r1);
		body_r1.addBox(-9.5F, -30.0F, -3.0F, 18.0F, 18.0F, 10.0F, 0.0F, false);

		head = new EndimatorModelRenderer(this, 0, 28);
		head.setRotationPoint(-9.0F, -27.0F, -6.0F);
		body.addChild(head);
		head.addBox(-5.0F, -10.0F, -4.0F, 10.0F, 10.0F, 10.0F, 0.0F, false);
		head.setTextureOffset(30, 28).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 6.0F, 4.0F, 0.0F, false);

		rightArm = new EndimatorModelRenderer(this, 0, 48);
		rightArm.setRotationPoint(-8.5F, -2.0F, 2.0F);
		rightArm.addBox(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);

		leftArm = new EndimatorModelRenderer(this, 0, 48);
		leftArm.setRotationPoint(13.5F, -2.0F, 2.0F);
		leftArm.addBox(-5.0F, -2.0F, -5.0F, 4.0F, 24.0F, 4.0F, 0.0F, false);
	}

	@Override
	public void setRotationAngles(E haunt, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch){
		body_r1.rotateAngleX = 0.2618F;

		this.rightArm.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * limbSwingAmount * 0.25F;
		this.leftArm.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F) * limbSwingAmount * 0.25F;
		this.rightArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * limbSwingAmount * 0.2F;
		this.leftArm.rotateAngleZ = MathHelper.cos(limbSwing * 0.1681F) * limbSwingAmount * 0.2F;

		this.rightLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F) * 0.3F * limbSwingAmount;
		this.leftLeg.rotateAngleX = MathHelper.cos(limbSwing * 0.1681F + (float)Math.PI) * 0.3F * limbSwingAmount;
	}

	@Override
	public void animateModel(E haunt) {
		super.animateModel(haunt);

		if (haunt.isEndimationPlaying(HauntEntity.ANGRY_ANIMATION)) {
			this.setEndimationToPlay(HauntEntity.ANGRY_ANIMATION);

			this.startKeyframe(25);
			this.rotate(this.body, -0.2F, 0.0F, 0.0F);
			this.scale(this.head, 0.2F, 0.2F, 0.2F);
			this.endKeyframe();

			this.resetKeyframe(5);

			this.startKeyframe(15);
			this.rotate(head, 0.0F, 0.0F, -0.2F);
			this.rotate(this.leftArm, -0.2F, 0.0F, -0.2F);
			this.rotate(this.rightArm, -0.2F, 0.0F, -0.2F);
			this.endKeyframe();

			this.resetKeyframe(5);

			this.startKeyframe(15);
			this.rotate(head, 0.0F, 0.0F, 0.2F);
			this.rotate(this.leftArm, -0.1F, 0.0F, -0.1F);
			this.rotate(this.rightArm, -0.1F, 0.0F, -0.1F);
			this.endKeyframe();

			this.resetKeyframe(5);

			this.startKeyframe(25);
			this.rotate(this.body, 0.0F, 0.0F, 0.0F);
			this.scale(this.head, 0.1F, 0.1F, 0.1F);
			this.endKeyframe();

			this.resetKeyframe(5);
		}
	}
}