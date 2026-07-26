package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.model.Dilation;
import net.minecraft.client.model.ModelData;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelPartBuilder;
import net.minecraft.client.model.ModelPartData;
import net.minecraft.client.model.ModelTransform;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.proctoredgames.saltcraft.entity.animations.ModAnimationDefinitions;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;

// Made with Blockbench, converted to Yarn mappings
public class FlamingoModel<T extends Flamingo> extends SinglePartEntityModel<T> {

	private final ModelPart flamingo_body;
	private final ModelPart left_leg;
	private final ModelPart left_leg_lower;
	private final ModelPart left_foot;
	private final ModelPart right_leg;
	private final ModelPart right_leg_lower;
	private final ModelPart right_foot;
	private final ModelPart body;
	private final ModelPart left_wing;
	private final ModelPart right_wing;
	private final ModelPart neck;
	private final ModelPart head;
	private final ModelPart beak;

	public FlamingoModel(ModelPart root) {
		this.flamingo_body = root.getChild("flamingo_body");
		this.left_leg = root.getChild("flamingo_body").getChild("left_leg");
		this.left_leg_lower = root.getChild("flamingo_body").getChild("left_leg").getChild("left_leg_lower");
		this.left_foot = root.getChild("flamingo_body").getChild("left_leg").getChild("left_leg_lower").getChild("left_foot");
		this.right_leg = root.getChild("flamingo_body").getChild("right_leg");
		this.right_leg_lower = root.getChild("flamingo_body").getChild("right_leg").getChild("right_leg_lower");
		this.right_foot = root.getChild("flamingo_body").getChild("right_leg").getChild("right_leg_lower").getChild("right_foot");
		this.body = root.getChild("flamingo_body").getChild("body");
		this.left_wing = root.getChild("flamingo_body").getChild("body").getChild("left_wing");
		this.right_wing = root.getChild("flamingo_body").getChild("body").getChild("right_wing");
		this.neck = root.getChild("flamingo_body").getChild("body").getChild("neck");
		this.head = root.getChild("flamingo_body").getChild("body").getChild("neck").getChild("head");
		this.beak = root.getChild("flamingo_body").getChild("body").getChild("neck").getChild("head").getChild("beak");

	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData flamingo_body = partdefinition.addChild("flamingo_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData left_leg = flamingo_body.addChild("left_leg", ModelPartBuilder.create().uv(0, 0).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -12.0F, 0.5F));

		ModelPartData left_leg_lower = left_leg.addChild("left_leg_lower", ModelPartBuilder.create().uv(8, 29).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData left_foot = left_leg_lower.addChild("left_foot", ModelPartBuilder.create().uv(16, 3).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -0.5F));

		ModelPartData body = flamingo_body.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-3.0F, -9.0F, -6.0F, 6.0F, 9.0F, 7.0F, new Dilation(0.0F))
				.uv(0, 16).cuboid(-3.0F, -8.0F, 1.0F, 6.0F, 8.0F, 5.0F, new Dilation(0.0F))
				.uv(24, 25).cuboid(-1.5F, -2.0F, -7.0F, 3.0F, 2.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 1.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(31, 25).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 5.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -4.5F, 6.0F, -0.3491F, 0.0F, 0.0F));

		ModelPartData left_wing = body.addChild("left_wing", ModelPartBuilder.create().uv(15, 25).cuboid(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 7.0F, new Dilation(0.0F))
				.uv(8, 36).cuboid(0.0F, 1.0F, 4.0F, 1.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, -9.0F, -3.0F));

		ModelPartData right_wing = body.addChild("right_wing", ModelPartBuilder.create().uv(22, 9).cuboid(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 7.0F, new Dilation(0.0F))
				.uv(31, 33).cuboid(-1.0F, 1.0F, 4.0F, 1.0F, 7.0F, 5.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, -9.0F, -3.0F));

		ModelPartData neck = body.addChild("neck", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, -1.0F, -7.0F));

		ModelPartData cube_r2 = neck.addChild("cube_r2", ModelPartBuilder.create().uv(0, 29).cuboid(-1.0F, -17.0F, -2.0F, 2.0F, 17.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 1.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		ModelPartData head = neck.addChild("head", ModelPartBuilder.create().uv(31, 0).cuboid(-1.5F, -4.0F, -5.0F, 3.0F, 4.0F, 6.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -15.0F, 0.0F));

		ModelPartData beak = head.addChild("beak", ModelPartBuilder.create().uv(20, 42).cuboid(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, new Dilation(0.0F))
				.uv(32, 45).cuboid(-1.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -2.0F, -5.0F, 0.0873F, 0.0F, 0.0F));

		ModelPartData right_leg = flamingo_body.addChild("right_leg", ModelPartBuilder.create().uv(42, 10).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -12.0F, 0.5F));

		ModelPartData right_leg_lower = right_leg.addChild("right_leg_lower", ModelPartBuilder.create().uv(38, 10).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 5.0F, 0.0F));

		ModelPartData right_foot = right_leg_lower.addChild("right_foot", ModelPartBuilder.create().uv(16, 0).cuboid(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 7.0F, -0.5F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(Flamingo entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.head.yaw = pNetHeadYaw * 0.017453292F;
		this.head.pitch = pHeadPitch * 0.017453292F;

		this.animateMovement(ModAnimationDefinitions.FLAMINGO_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.standOnOneLegAnimationState, ModAnimationDefinitions.FLAMINGO_STAND_ON_ONE_LEG, pAgeInTicks, 1f);

	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		flamingo_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart getPart() {
		return flamingo_body;
	}

}