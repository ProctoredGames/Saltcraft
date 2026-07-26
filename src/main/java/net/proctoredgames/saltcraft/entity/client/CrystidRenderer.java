package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Crystid;

public class CrystidRenderer extends MobEntityRenderer<Crystid, CrystidModel<Crystid>> {
    private static final Identifier CRYSTID_LOCATION = Identifier.of(Saltcraft.MOD_ID, "textures/entity/crystid/crystid.png");

    public CrystidRenderer(EntityRendererFactory.Context context) {
        super(context, new CrystidModel<>(context.getPart(ModModelLayers.CRYSTID_LAYER)), 0.5f);
        this.addFeature(new CrystidEyesLayer(this));
    }

    @Override
    public Identifier getTexture(Crystid entity) {
        return CRYSTID_LOCATION;
    }

    @Override
    public void render(Crystid entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) {
            matrices.scale(0.6f, 0.6f, 0.6f);
        }
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
