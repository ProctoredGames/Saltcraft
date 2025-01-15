package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.EyesLayer;
import net.minecraft.resources.ResourceLocation;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.Saltcraft;

public class MirageEyesLayer extends EyesLayer<Mirage, MirageModel<Mirage>> {
    private static final RenderType EYES = RenderType.eyes(new ResourceLocation(Saltcraft.MOD_ID, "textures/entity/mirage/mirage_eyes.png"));

    public MirageEyesLayer(RenderLayerParent<Mirage, MirageModel<Mirage>> renderer) {
        super(renderer);
    }

    @Override
    public RenderType renderType() {
        return EYES;
    }
}
