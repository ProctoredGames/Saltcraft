package net.proctoredgames.saltcraft.capes;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.minecraft.client.model.PlayerModel;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.entity.player.PlayerRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.phys.Vec3;
import net.proctoredgames.saltcraft.capes.CapeHandler;

public class CustomCapeLayer extends RenderLayer<AbstractClientPlayer, PlayerModel<AbstractClientPlayer>> {
    public CustomCapeLayer(PlayerRenderer renderer) {
        super(renderer);
    }

    @Override
    public void render(PoseStack poseStack, MultiBufferSource buffer, int light, AbstractClientPlayer player, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
        String capePath = CapeHandler.getCape(player.getUUID());
        if (capePath == null || player.isInvisible() || !player.isCapeLoaded() || player.isSleeping()) {
            return;
        }

        ResourceLocation capeTexture = new ResourceLocation(capePath);

        poseStack.pushPose();
        poseStack.translate(0.0D, 0.0D, 0.125D); // Slight offset for cape positioning
        this.getParentModel().renderCloak(poseStack, buffer.getBuffer(RenderType.entitySolid(capeTexture)), light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();

        Vec3 motion = player.getDeltaMovement();
        double d0 = Math.min(motion.length(), 1.0);
        float f = (float) d0 * 10.0F;
        f = Math.max(f, 1.0F);

        poseStack.mulPose(Axis.XP.rotationDegrees(f));
        poseStack.mulPose(Axis.YP.rotationDegrees(netHeadYaw));

        this.getParentModel().renderCloak(poseStack, buffer.getBuffer(RenderType.entitySolid(capeTexture)), light, OverlayTexture.NO_OVERLAY);
        poseStack.popPose();
    }
}
