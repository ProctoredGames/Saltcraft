package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;

public class FlamingoRenderer extends MobEntityRenderer<Flamingo, FlamingoModel<Flamingo>> {
    private static final Identifier PINK = Identifier.of(Saltcraft.MOD_ID, "textures/entity/flamingo/flamingo_pink.png");
    private static final Identifier WHITE = Identifier.of(Saltcraft.MOD_ID, "textures/entity/flamingo/flamingo_white.png");

    public FlamingoRenderer(EntityRendererFactory.Context context) {
        super(context, new FlamingoModel<>(context.getPart(ModModelLayers.FLAMINGO_LAYER)), 0.4f);
    }

    @Override
    public Identifier getTexture(Flamingo entity) {
        return switch (entity.getVariant()) {
            case PINK -> PINK;
            case WHITE -> WHITE;
        };
    }

    @Override
    public void render(Flamingo entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
