package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class CrystidEyesLayer extends EyesLayer<Crystid, CrystidModel<Crystid>> {
    // Define the RenderType for the emissive eyes
    private static final RenderType EYES = RenderType.eyes(
            new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/crystid/crystid_eyes.png")
    );

    public CrystidEyesLayer(RenderLayerParent<Crystid, CrystidModel<Crystid>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return EYES; // Return the RenderType for the glowing eyes
    }
}
