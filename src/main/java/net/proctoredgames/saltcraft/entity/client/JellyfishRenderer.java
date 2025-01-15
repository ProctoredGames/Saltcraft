package net.proctoredgames.saltcraft.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class JellyfishRenderer extends MobRenderer<Jellyfish, JellyfishModel<Jellyfish>> {
    private static final ResourceLocation BLUE = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_blue.png");
    private static final ResourceLocation CYAN = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_cyan.png");
    private static final ResourceLocation ORANGE = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_orange.png");
    private static final ResourceLocation PINK = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_pink.png");
    private static final ResourceLocation PURPLE = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_purple.png");
    private static final ResourceLocation RED = new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_red.png");


    public JellyfishRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new JellyfishModel<>(pContext.bakeLayer(ModModelLayers.JELLYFISH_LAYER)), 0.4f);
    }

    @Override
    public ResourceLocation getTextureLocation(Jellyfish pEntity) {
        return getVariantTexture(pEntity.getVariant());
    }

    public static ResourceLocation getVariantTexture(Jellyfish.Variant pVariant) {
        ResourceLocation var10000;
        switch (pVariant) {
            case BLUE -> var10000 = BLUE;
            case CYAN -> var10000 = CYAN;
            case ORANGE -> var10000 = ORANGE;
            case PINK -> var10000 = PINK;
            case PURPLE -> var10000 = PURPLE;
            case RED -> var10000 = RED;
            default -> throw new IncompatibleClassChangeError();
        }

        return var10000;
    }

    @Override
    public void render(Jellyfish pEntity, float pEntityYaw, float pPartialTicks, PoseStack pMatrixStack,
                       MultiBufferSource pBuffer, int pPackedLight) {
        if(pEntity.isBaby()) {
            pMatrixStack.scale(0.5f, 0.5f, 0.5f);
        }

        super.render(pEntity, pEntityYaw, pPartialTicks, pMatrixStack, pBuffer, pPackedLight);
    }

}