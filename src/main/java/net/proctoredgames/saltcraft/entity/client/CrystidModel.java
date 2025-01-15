package net.proctoredgames.saltcraft.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.util.Mth;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.entity.animations.ModAnimationDefinitions;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;

public class CrystidModel<T extends Crystid> extends HierarchicalModel<T> {

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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition crystid_body = partdefinition.addOrReplaceChild("crystid_body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition head = crystid_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 30).addBox(-5.0F, -4.0F, -4.0F, 10.0F, 6.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(-4, 24).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 0.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, -6.0F));

		PartDefinition jaw = head.addOrReplaceChild("jaw", CubeListBuilder.create().texOffs(28, 30).addBox(-5.0F, 0.0F, -4.0F, 10.0F, 2.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.0F, 0.0F));

		PartDefinition leg_fl = crystid_body.addOrReplaceChild("leg_fl", CubeListBuilder.create().texOffs(0, 40).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -4.0F, -4.0F));

		PartDefinition leg_fr = crystid_body.addOrReplaceChild("leg_fr", CubeListBuilder.create().texOffs(36, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -4.0F, -4.0F));

		PartDefinition leg_br = crystid_body.addOrReplaceChild("leg_br", CubeListBuilder.create().texOffs(24, 36).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-5.0F, -4.0F, 4.0F));

		PartDefinition leg_bl = crystid_body.addOrReplaceChild("leg_bl", CubeListBuilder.create().texOffs(48, 8).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 4.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(5.0F, -4.0F, 4.0F));

		PartDefinition body = crystid_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-6.0F, -6.0F, -6.0F, 12.0F, 12.0F, 12.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -10.0F, 0.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(40, 33).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 1.5708F, -0.7854F, -1.5708F));

		PartDefinition cube_r2 = body.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(16, 37).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(8.0F, 0.0F, 0.0F, 1.5708F, -2.3562F, -1.5708F));

		PartDefinition cube_r3 = body.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(28, 41).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -1.5708F, -0.7854F, 0.0F));

		PartDefinition cube_r4 = body.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(22, 41).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -8.0F, 0.0F, -1.5708F, 0.7854F, 0.0F));

		PartDefinition cube_r5 = body.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 45).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 1.5708F, 0.7854F, 1.5708F));

		PartDefinition cube_r6 = body.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(34, 41).addBox(0.0F, -7.0F, -1.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-8.0F, 0.0F, 0.0F, 1.5708F, -0.7854F, 1.5708F));

		PartDefinition cube_r7 = body.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(6, 45).addBox(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.0F, 0.0F, -0.7854F));

		PartDefinition cube_r8 = body.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(46, 33).addBox(0.0F, -7.0F, 0.0F, 0.0F, 14.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 0.0F, 6.0F, 0.0F, 0.0F, 0.7854F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Crystid entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = pNetHeadYaw * 0.017453292F;
		this.head.xRot = pHeadPitch * 0.017453292F;

		this.animateWalk(ModAnimationDefinitions.CRYSTID_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.CRYSTID_ATTACK, pAgeInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		crystid_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return crystid_body;
	}

}