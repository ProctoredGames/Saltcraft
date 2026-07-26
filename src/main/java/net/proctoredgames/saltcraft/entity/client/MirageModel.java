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
import net.proctoredgames.saltcraft.entity.custom.Mirage;

// Made with Blockbench, converted to Yarn mappings
public class MirageModel<T extends Mirage> extends SinglePartEntityModel<T> {
	// This layer location should be baked with EntityRendererProvider.Context in the entity renderer and passed into this model's constructor
	private final ModelPart mirage_body;
	private final ModelPart right_arm;
	private final ModelPart head;
	private final ModelPart left_head_flap;
	private final ModelPart right_head_flap;
	private final ModelPart left_arm;
	private final ModelPart right_leg;
	private final ModelPart left_leg;
	private final ModelPart body;

	public MirageModel(ModelPart root) {
		this.mirage_body = root.getChild("mirage_body");
		this.right_arm = this.mirage_body.getChild("right_arm");
		this.head = this.mirage_body.getChild("head");
		this.left_head_flap = this.head.getChild("left_head_flap");
		this.right_head_flap = this.head.getChild("right_head_flap");
		this.left_arm = this.mirage_body.getChild("left_arm");
		this.right_leg = this.mirage_body.getChild("right_leg");
		this.left_leg = this.mirage_body.getChild("left_leg");
		this.body = this.mirage_body.getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData mirage_body = partdefinition.addChild("mirage_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData right_arm = mirage_body.addChild("right_arm", ModelPartBuilder.create().uv(24, 16).cuboid(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(4.0F, -22.0F, -1.0F));

		ModelPartData head = mirage_body.addChild("head", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -24.0F, -1.0F));

		ModelPartData left_head_flap = head.addChild("left_head_flap", ModelPartBuilder.create().uv(32, 32).cuboid(-4.0F, -4.0F, 0.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F))
				.uv(32, 40).cuboid(-4.0F, -4.0F, 0.01F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(-4.0F, -4.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		ModelPartData right_head_flap = head.addChild("right_head_flap", ModelPartBuilder.create().uv(40, 16).cuboid(0.0F, -4.0F, 0.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F))
				.uv(40, 24).cuboid(0.0F, -4.0F, 0.01F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(4.0F, -4.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		ModelPartData left_arm = mirage_body.addChild("left_arm", ModelPartBuilder.create().uv(0, 32).cuboid(-4.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-4.0F, -22.0F, -1.0F));

		ModelPartData right_leg = mirage_body.addChild("right_leg", ModelPartBuilder.create().uv(32, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(2.0F, -12.0F, 0.0F));

		ModelPartData left_leg = mirage_body.addChild("left_leg", ModelPartBuilder.create().uv(16, 32).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-2.0F, -12.0F, 0.0F));

		ModelPartData body = mirage_body.addChild("body", ModelPartBuilder.create().uv(0, 16).cuboid(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -12.0F, 0.0F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(Mirage entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.head.yaw = pNetHeadYaw * 0.017453292F;
		this.head.pitch = pHeadPitch * 0.017453292F;

		this.animateMovement(ModAnimationDefinitions.MIRAGE_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.attackAnimationState, ModAnimationDefinitions.MIRAGE_PUNCH, pAgeInTicks, 1f);
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		mirage_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart getPart() {
		return mirage_body;
	}
}