package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageEyesLayer extends EyesFeatureRenderer<Mirage, MirageModel<Mirage>> {
    private static final RenderLayer EYES = RenderLayer.getEyes(
            Identifier.of(Saltcraft.MOD_ID, "textures/entity/mirage/mirage_eyes.png"));

    public MirageEyesLayer(FeatureRendererContext<Mirage, MirageModel<Mirage>> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
