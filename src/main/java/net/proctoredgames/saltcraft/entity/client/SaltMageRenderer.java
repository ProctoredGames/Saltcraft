package net.proctoredgames.saltcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SaltMageRenderer extends MobRenderer<SaltMage, SaltMageModel<SaltMage>> {
    public SaltMageRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new SaltMageModel<>(pContext.bakeLayer(ModModelLayers.SALT_MAGE_LAYER)), 0.5f);
        // Add the eyes layer
        this.addLayer(new SaltMageEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(SaltMage pEntity) {
        return new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/salt_mage/salt_mage.png");
    }

    @Override
    public void render(SaltMage pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
