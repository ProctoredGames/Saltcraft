package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;

public class SaltMageRenderer extends MobEntityRenderer<SaltMage, SaltMageModel<SaltMage>> {
    private static final Identifier SALT_MAGE_LOCATION = Identifier.of(Saltcraft.MOD_ID, "textures/entity/salt_mage/salt_mage.png");

    public SaltMageRenderer(EntityRendererFactory.Context context) {
        super(context, new SaltMageModel<>(context.getPart(ModModelLayers.SALT_MAGE_LAYER)), 0.5f);
        this.addFeature(new SaltMageEyesLayer(this));
    }

    @Override
    public Identifier getTexture(SaltMage entity) {
        return SALT_MAGE_LOCATION;
    }
}
