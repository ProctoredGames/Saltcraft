package net.proctoredgames.saltcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.animal.Parrot;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;

public class FlamingoRenderer extends MobRenderer<Flamingo, FlamingoModel<Flamingo>> {
    private static final ResourceLocation PINK = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/flamingo/flamingo_pink.png");
    private static final ResourceLocation WHITE = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/flamingo/flamingo_white.png");

    public FlamingoRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new FlamingoModel<>(pContext.bakeLayer(ModModelLayers.FLAMINGO_LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Flamingo pEntity) {
        return getVariantTexture(pEntity.getVariant());
    }

    public static ResourceLocation getVariantTexture(Flamingo.Variant pVariant) {
        ResourceLocation var10000;
        switch (pVariant) {
            case PINK -> var10000 = PINK;
            case WHITE -> var10000 = WHITE;
            default -> throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    @Override
    public void render(Flamingo pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}