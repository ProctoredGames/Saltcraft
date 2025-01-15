package net.proctoredgames.saltcraft.entity.client;// Made with Blockbench 4.10.4
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.proctoredgames.saltcraft.entity.animations.ModAnimationDefinitions;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;

public class FlamingoModel<T extends Flamingo> extends HierarchicalModel<T> {

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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition flamingo_body = partdefinition.addOrReplaceChild("flamingo_body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition left_leg = flamingo_body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.5F));

		PartDefinition left_leg_lower = left_leg.addOrReplaceChild("left_leg_lower", CubeListBuilder.create().texOffs(8, 29).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition left_foot = left_leg_lower.addOrReplaceChild("left_foot", CubeListBuilder.create().texOffs(16, 3).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -0.5F));

		PartDefinition body = flamingo_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -9.0F, -6.0F, 6.0F, 9.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(0, 16).addBox(-3.0F, -8.0F, 1.0F, 6.0F, 8.0F, 5.0F, new CubeDeformation(0.0F))
				.texOffs(24, 25).addBox(-1.5F, -2.0F, -7.0F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 1.0F));

		PartDefinition cube_r1 = body.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(31, 25).addBox(-2.0F, 0.0F, 0.0F, 4.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -4.5F, 6.0F, -0.3491F, 0.0F, 0.0F));

		PartDefinition left_wing = body.addOrReplaceChild("left_wing", CubeListBuilder.create().texOffs(15, 25).addBox(0.0F, 0.0F, -3.0F, 1.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(8, 36).addBox(0.0F, 1.0F, 4.0F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(3.0F, -9.0F, -3.0F));

		PartDefinition right_wing = body.addOrReplaceChild("right_wing", CubeListBuilder.create().texOffs(22, 9).addBox(-1.0F, 0.0F, -3.0F, 1.0F, 8.0F, 7.0F, new CubeDeformation(0.0F))
				.texOffs(31, 33).addBox(-1.0F, 1.0F, 4.0F, 1.0F, 7.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.0F, -9.0F, -3.0F));

		PartDefinition neck = body.addOrReplaceChild("neck", CubeListBuilder.create(), PartPose.offset(0.0F, -1.0F, -7.0F));

		PartDefinition cube_r2 = neck.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(0, 29).addBox(-1.0F, -17.0F, -2.0F, 2.0F, 17.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 1.0F, 0.0F, -0.0436F, 0.0F, 0.0F));

		PartDefinition head = neck.addOrReplaceChild("head", CubeListBuilder.create().texOffs(31, 0).addBox(-1.5F, -4.0F, -5.0F, 3.0F, 4.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -15.0F, 0.0F));

		PartDefinition beak = head.addOrReplaceChild("beak", CubeListBuilder.create().texOffs(20, 42).addBox(-1.0F, 0.0F, -4.0F, 2.0F, 2.0F, 4.0F, new CubeDeformation(0.0F))
				.texOffs(32, 45).addBox(-1.0F, 2.0F, -4.0F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, -5.0F, 0.0873F, 0.0F, 0.0F));

		PartDefinition right_leg = flamingo_body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(42, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 5.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.5F));

		PartDefinition right_leg_lower = right_leg.addOrReplaceChild("right_leg_lower", CubeListBuilder.create().texOffs(38, 10).addBox(-0.5F, 0.0F, -0.5F, 1.0F, 7.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 5.0F, 0.0F));

		PartDefinition right_foot = right_leg_lower.addOrReplaceChild("right_foot", CubeListBuilder.create().texOffs(16, 0).addBox(-1.5F, 0.0F, -3.0F, 3.0F, 0.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 7.0F, -0.5F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Flamingo entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = pNetHeadYaw * 0.017453292F;
		this.head.xRot = pHeadPitch * 0.017453292F;

		this.animateWalk(ModAnimationDefinitions.FLAMINGO_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.standOnOneLegAnimationState, ModAnimationDefinitions.FLAMINGO_STAND_ON_ONE_LEG, pAgeInTicks, 1f);

	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		flamingo_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return flamingo_body;
	}

}