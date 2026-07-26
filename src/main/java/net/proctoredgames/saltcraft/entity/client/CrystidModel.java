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
import net.proctoredgames.saltcraft.entity.custom.Crystid;

// Made with Blockbench, converted to Yarn mappings
public class CrystidModel<T extends Crystid> extends SinglePartEntityModel<T> {

	private final ModelPart crystid_body;
	private final ModelPart head;
	private final ModelPart jaw;
	private final ModelPart leg_fl;
	private final ModelPart leg_fr;
	private final ModelPart leg_bl;
	private final ModelPart leg_br;
	private final ModelPart body;

	public CrystidModel(ModelPart root) {
		this.crystid_body = root.getChild("crystid_body");
		this.head = root.getChild("crystid_body").getChild("head");
		this.jaw = root.getChild("crystid_body").getChild("head").getChild("jaw");
		this.leg_fl = root.getChild("crystid_body").getChild("leg_fl");
		this.leg_fr = root.getChild("crystid_body").getChild("leg_fr");
		this.leg_bl = root.getChild("crystid_body").getChild("leg_bl");
		this.leg_br = root.getChild("crystid_body").getChild("leg_br");
		this.body = root.getChild("crystid_body").getChild("body");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData crystid_body = partdefinition.addChild("crystid_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData head = crystid_body.addChild("head", ModelPartBuilder.create().uv(0, 30).cuboid(-5.0F, -4.0F, -4.0F, 10.0F, 6.0F, 4.0F, new Dilation(0.0F))
				.uv(-4, 24).cuboid(-5.0F, 0.0F, -4.0F, 10.0F, 0.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -10.0F, -6.0F));

		ModelPartData jaw = head.addChild("jaw", ModelPartBuilder.create().uv(28, 30).cuboid(-5.0F, 0.0F, -4.0F, 10.0F, 2.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 2.0F, 0.0F));

		ModelPartData leg_fl = crystid_body.addChild("leg_fl", ModelPartBuilder.create().uv(0, 40).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -4.0F, -4.0F));

		ModelPartData leg_fr = crystid_body.addChild("leg_fr", ModelPartBuilder.create().uv(36, 0).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -4.0F, -4.0F));

		ModelPartData leg_br = crystid_body.addChild("leg_br", ModelPartBuilder.create().uv(24, 36).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(-5.0F, -4.0F, 4.0F));

		ModelPartData leg_bl = crystid_body.addChild("leg_bl", ModelPartBuilder.create().uv(48, 8).cuboid(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new Dilation(0.0F)), ModelTransform.pivot(5.0F, -4.0F, 4.0F));

		ModelPartData body = crystid_body.addChild("body", ModelPartBuilder.create().uv(0, 0).cuboid(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, -10.0F, 0.0F));

		ModelPartData cube_r1 = body.addChild("cube_r1", ModelPartBuilder.create().uv(40, 33).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 0.0F, 0.0F, 1.5708F, -0.7854F, -1.5708F));

		ModelPartData cube_r2 = body.addChild("cube_r2", ModelPartBuilder.create().uv(16, 37).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(8.0F, 0.0F, 0.0F, 1.5708F, -2.3562F, -1.5708F));

		ModelPartData cube_r3 = body.addChild("cube_r3", ModelPartBuilder.create().uv(28, 41).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 0.0F, -1.5708F, -0.7854F, 0.0F));

		ModelPartData cube_r4 = body.addChild("cube_r4", ModelPartBuilder.create().uv(22, 41).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -8.0F, 0.0F, -1.5708F, 0.7854F, 0.0F));

		ModelPartData cube_r5 = body.addChild("cube_r5", ModelPartBuilder.create().uv(0, 45).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.0F, 0.0F, 1.5708F, 0.7854F, 1.5708F));

		ModelPartData cube_r6 = body.addChild("cube_r6", ModelPartBuilder.create().uv(34, 41).cuboid(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(-8.0F, 0.0F, 0.0F, 1.5708F, -0.7854F, 1.5708F));

		ModelPartData cube_r7 = body.addChild("cube_r7", ModelPartBuilder.create().uv(6, 45).cuboid(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 6.0F, 0.0F, 0.0F, -0.7854F));

		ModelPartData cube_r8 = body.addChild("cube_r8", ModelPartBuilder.create().uv(46, 33).cuboid(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.7854F));

		return TexturedModelData.of(meshdefinition, 64, 64);
	}

	@Override
	public void setAngles(Crystid entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);
		this.head.yaw = pNetHeadYaw * 0.017453292F;
		this.head.pitch = pHeadPitch * 0.017453292F;

		this.animateMovement(ModAnimationDefinitions.CRYSTID_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.updateAnimation(entity.attackAnimationState, ModAnimationDefinitions.CRYSTID_ATTACK, pAgeInTicks, 1f);
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		crystid_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart getPart() {
		return crystid_body;
	}

}