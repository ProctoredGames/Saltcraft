package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SaltMageEyesLayer extends EyesLayer<SaltMage, SaltMageModel<SaltMage>> {
    // Define the emissive texture
    private static final RenderType EYES = RenderType.eyes(
            new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/salt_mage/salt_mage_eyes.png")
    );

    public SaltMageEyesLayer(RenderLayerParent<SaltMage, SaltMageModel<SaltMage>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return EYES; // Return the emissive texture's RenderType
    }
}
