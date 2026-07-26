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
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.minecraft.entity.Entity;

// Made with Blockbench, converted to Yarn mappings
public class JellyfishModel<T extends Entity> extends SinglePartEntityModel<T> {

	private final ModelPart jellyfish_body;
	private final ModelPart hood;
	private final ModelPart tentacles;
	private final ModelPart tentacle_1;
	private final ModelPart tentacle_2;
	private final ModelPart tentacle_3;
	private final ModelPart tentacle_4;
	private final ModelPart tentacle_5;
	private final ModelPart tentacle_6;
	private final ModelPart tentacle_7;
	private final ModelPart tentacle_8;
	private final ModelPart oral_arms;

	public JellyfishModel(ModelPart root) {
		this.jellyfish_body = root.getChild("jellyfish_body");
		this.hood = root.getChild("jellyfish_body").getChild("hood");
		this.tentacles = root.getChild("jellyfish_body").getChild("tentacles");
		this.tentacle_1 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_1");
		this.tentacle_2 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_2");
		this.tentacle_3 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_3");
		this.tentacle_4 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_4");
		this.tentacle_5 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_5");
		this.tentacle_6 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_6");
		this.tentacle_7 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_7");
		this.tentacle_8 = root.getChild("jellyfish_body").getChild("tentacles").getChild("tentacle_8");
		this.oral_arms = root.getChild("jellyfish_body").getChild("oral_arms");
	}

	public static TexturedModelData getTexturedModelData() {
		ModelData meshdefinition = new ModelData();
		ModelPartData partdefinition = meshdefinition.getRoot();

		ModelPartData jellyfish_body = partdefinition.addChild("jellyfish_body", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

		ModelPartData hood = jellyfish_body.addChild("hood", ModelPartBuilder.create().uv(0, 0).cuboid(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tentacles = jellyfish_body.addChild("tentacles", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData tentacle_1 = tentacles.addChild("tentacle_1", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.25F, 0.0F, -3.0F));

		ModelPartData tentacle_2 = tentacles.addChild("tentacle_2", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.25F, 0.0F, -3.0F));

		ModelPartData tentacle_3 = tentacles.addChild("tentacle_3", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, -1.25F));

		ModelPartData tentacle_4 = tentacles.addChild("tentacle_4", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-3.0F, 0.0F, 1.25F));

		ModelPartData tentacle_5 = tentacles.addChild("tentacle_5", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(-1.25F, 0.0F, 3.0F));

		ModelPartData tentacle_6 = tentacles.addChild("tentacle_6", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(1.25F, 0.0F, 3.0F));

		ModelPartData tentacle_7 = tentacles.addChild("tentacle_7", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, 1.25F));

		ModelPartData tentacle_8 = tentacles.addChild("tentacle_8", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new Dilation(0.0F)), ModelTransform.pivot(3.0F, 0.0F, -1.25F));

		ModelPartData oral_arms = jellyfish_body.addChild("oral_arms", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = oral_arms.addChild("cube_r1", ModelPartBuilder.create().uv(16, 12).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		ModelPartData cube_r2 = oral_arms.addChild("cube_r2", ModelPartBuilder.create().uv(16, 24).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return TexturedModelData.of(meshdefinition, 32, 32);
	}

	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.getPart().traverse().forEach(ModelPart::resetTransform);

		this.animateMovement(ModAnimationDefinitions.JELLYFISH_DRIFT, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.updateAnimation(((Jellyfish) entity).idleAnimationState, ModAnimationDefinitions.JELLYFISH_IDLE, ageInTicks, 1f);
	}

	@Override
	public void render(MatrixStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, int color) {
		jellyfish_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, color);
	}

	@Override
	public ModelPart getPart() {
		return jellyfish_body;
	}
}