package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.Saltcraft;

public class MirageRenderer extends MobRenderer<Mirage, MirageModel<Mirage>> {
    public MirageRenderer(EntityRendererProvider.Context pContext) {
        super(pContext, new MirageModel<>(pContext.bakeLayer(ModModelLayers.MIRAGE_LAYER)), 0.0f);
        this.addLayer(new MirageEyesLayer(this));
    }

    @Override
    public ResourceLocation getTextureLocation(Mirage pEntity) {
        return new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/mirage/mirage.png");
    }
}
