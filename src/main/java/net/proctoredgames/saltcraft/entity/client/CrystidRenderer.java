package net.proctoredgames.saltcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class CrystidRenderer extends MobRenderer<Crystid, CrystidModel<Crystid>> {
    private static final ResourceLocation CRYSTID_LOCATION = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/crystid/crystid.png");

    public CrystidRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new CrystidModel<>(pContext.bakeLayer(ModModelLayers.CRYSTID_LAYER)), 0.5f);
        this.addLayer(new CrystidEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Crystid pEntity) {
        return CRYSTID_LOCATION;
    }

    @Override
    public void render(Crystid pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if (pEntity.isBaby()) {
            pMatrixStack.scale(0.6f, 0.6f, 0.6f);
        }
        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }
}
