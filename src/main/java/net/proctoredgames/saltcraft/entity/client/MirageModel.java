package net.proctoredgames.saltcraft.entity.client;// Made with Blockbench 4.11.2
// Exported for Minecraft version 1.17 or later with Mojang mappings
// Paste this class into your mod and generate all required imports


import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.model.HierarchicalModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.world.entity.Entity;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.entity.animations.ModAnimationDefinitions;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class MirageModel<T extends Mirage> extends HierarchicalModel<T> {
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

	public static LayerDefinition createBodyLayer() {
		MeshDefinition meshdefinition = new MeshDefinition();
		PartDefinition partdefinition = meshdefinition.getRoot();

		PartDefinition mirage_body = partdefinition.addOrReplaceChild("mirage_body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

		PartDefinition right_arm = mirage_body.addOrReplaceChild("right_arm", CubeListBuilder.create().texOffs(24, 16).addBox(0.0F, -2.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(4.0F, -22.0F, -1.0F));

		PartDefinition head = mirage_body.addOrReplaceChild("head", CubeListBuilder.create().texOffs(0, 0).addBox(-4.0F, -8.0F, -4.0F, 8.0F, 8.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -24.0F, -1.0F));

		PartDefinition left_head_flap = head.addOrReplaceChild("left_head_flap", CubeListBuilder.create().texOffs(32, 32).addBox(-4.0F, -4.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(32, 40).addBox(-4.0F, -4.0F, 0.01F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-4.0F, -4.0F, -2.0F, 0.0F, -0.2618F, 0.0F));

		PartDefinition right_head_flap = head.addOrReplaceChild("right_head_flap", CubeListBuilder.create().texOffs(40, 16).addBox(0.0F, -4.0F, 0.0F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F))
				.texOffs(40, 24).addBox(0.0F, -4.0F, 0.01F, 4.0F, 8.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(4.0F, -4.0F, -2.0F, 0.0F, 0.2618F, 0.0F));

		PartDefinition left_arm = mirage_body.addOrReplaceChild("left_arm", CubeListBuilder.create().texOffs(0, 32).addBox(-4.0F, -2.0F, -2.0F, 4.0F, 14.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-4.0F, -22.0F, -1.0F));

		PartDefinition right_leg = mirage_body.addOrReplaceChild("right_leg", CubeListBuilder.create().texOffs(32, 0).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(2.0F, -12.0F, 0.0F));

		PartDefinition left_leg = mirage_body.addOrReplaceChild("left_leg", CubeListBuilder.create().texOffs(16, 32).addBox(-2.0F, 0.0F, -2.0F, 4.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(-2.0F, -12.0F, 0.0F));

		PartDefinition body = mirage_body.addOrReplaceChild("body", CubeListBuilder.create().texOffs(0, 16).addBox(-4.0F, -12.0F, -2.0F, 8.0F, 12.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -12.0F, 0.0F));

		return LayerDefinition.create(meshdefinition, 64, 64);
	}

	@Override
	public void setupAnim(Mirage entity, float pLimbSwing, float pLimbSwingAmount, float pAgeInTicks, float pNetHeadYaw, float pHeadPitch) {
		this.root().getAllParts().forEach(ModelPart::resetPose);
		this.head.yRot = pNetHeadYaw * 0.017453292F;
		this.head.xRot = pHeadPitch * 0.017453292F;

		this.animateWalk(ModAnimationDefinitions.MIRAGE_WALK, pLimbSwing, pLimbSwingAmount, 2f, 2.5f);
		this.animate(entity.attackAnimationState, ModAnimationDefinitions.MIRAGE_PUNCH, pAgeInTicks, 1f);
	}

	@Override
	public void renderToBuffer(PoseStack poseStack, VertexConsumer vertexConsumer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
		mirage_body.render(poseStack, vertexConsumer, packedLight, packedOverlay, red, green, blue, alpha);
	}

	@Override
	public ModelPart root() {
		return mirage_body;
	}
}