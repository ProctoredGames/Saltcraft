package net.proctoredgames.saltcraft.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class JellyfishModel<T extends Entity> extends HierarchicalModel<T> {

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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition jellyfish_body = partdefinition.addOrReplaceChild("jellyfish_body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition hood = jellyfish_body.addOrReplaceChild("hood", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -4.0F, -4.0F, 8.0F, 4.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tentacles = jellyfish_body.addOrReplaceChild("tentacles", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition tentacle_1 = tentacles.addOrReplaceChild("tentacle_1", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 0.0F, -3.0F));

		PartDefinition tentacle_2 = tentacles.addOrReplaceChild("tentacle_2", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 0.0F, -3.0F));

		PartDefinition tentacle_3 = tentacles.addOrReplaceChild("tentacle_3", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, -1.25F));

		PartDefinition tentacle_4 = tentacles.addOrReplaceChild("tentacle_4", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, 0.0F, 1.25F));

		PartDefinition tentacle_5 = tentacles.addOrReplaceChild("tentacle_5", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.25F, 0.0F, 3.0F));

		PartDefinition tentacle_6 = tentacles.addOrReplaceChild("tentacle_6", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(1.25F, 0.0F, 3.0F));

		PartDefinition tentacle_7 = tentacles.addOrReplaceChild("tentacle_7", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, 1.25F));

		PartDefinition tentacle_8 = tentacles.addOrReplaceChild("tentacle_8", CubeListBuilder.create().texOffs(0, 15).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 12.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, 0.0F, -1.25F));

		PartDefinition oral_arms = jellyfish_body.addOrReplaceChild("oral_arms", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

		PartDefinition cube_r1 = oral_arms.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(16, 12).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -2.3562F, 0.0F));

		PartDefinition cube_r2 = oral_arms.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 24).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 0.0F, 0.0F, -0.7854F, 0.0F));

		return LayerDefinition.create(meshdefinition, 32, 32);
	}

	@Override
	public void setupAnim(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);

		this.animateWalk(ModAnimationDefinitions.JELLYFISH_DRIFT, limbSwing, limbSwingAmount, 2f, 2.5f);
		this.animate(((Jellyfish) entity).idleAnimationState, ModAnimationDefinitions.JELLYFISH_IDLE, ageInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		jellyfish_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return jellyfish_body;
	}
}