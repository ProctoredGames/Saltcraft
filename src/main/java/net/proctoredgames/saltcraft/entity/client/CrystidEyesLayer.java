package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class CrystidEyesLayer extends EyesFeatureRenderer<Crystid, CrystidModel<Crystid>> {
    private static final RenderLayer EYES = RenderLayer.getEyes(
            Identifier.of(Saltcraft.MOD_ID, "textures/entity/crystid/crystid_eyes.png"));

    public CrystidEyesLayer(FeatureRendererContext<Crystid, CrystidModel<Crystid>> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
