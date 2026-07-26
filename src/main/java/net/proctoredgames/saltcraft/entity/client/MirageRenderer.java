package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Mirage;

public class MirageRenderer extends MobEntityRenderer<Mirage, MirageModel<Mirage>> {
    private static final Identifier MIRAGE_LOCATION = Identifier.of(Saltcraft.MOD_ID, "textures/entity/mirage/mirage.png");

    public MirageRenderer(EntityRendererFactory.Context context) {
        super(context, new MirageModel<>(context.getPart(ModModelLayers.MIRAGE_LAYER)), 0.0f);
        this.addFeature(new MirageEyesLayer(this));
    }

    @Override
    public Identifier getTexture(Mirage entity) {
        return MIRAGE_LOCATION;
    }
}
