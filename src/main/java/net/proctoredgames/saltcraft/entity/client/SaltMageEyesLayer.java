package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.entity.feature.EyesFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SaltMageEyesLayer extends EyesFeatureRenderer<SaltMage, SaltMageModel<SaltMage>> {
    private static final RenderLayer EYES = RenderLayer.getEyes(
            Identifier.of(Saltcraft.MOD_ID, "textures/entity/salt_mage/salt_mage_eyes.png"));

    public SaltMageEyesLayer(FeatureRendererContext<SaltMage, SaltMageModel<SaltMage>> context) {
        super(context);
    }

    @Override
    public RenderLayer getEyesTexture() {
        return EYES;
    }
}
