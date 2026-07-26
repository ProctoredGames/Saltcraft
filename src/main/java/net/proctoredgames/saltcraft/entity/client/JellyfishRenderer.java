package net.proctoredgames.saltcraft.entity.client;

import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;

public class JellyfishRenderer extends MobEntityRenderer<Jellyfish, JellyfishModel<Jellyfish>> {
    private static final Identifier BLUE = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_blue.png");
    private static final Identifier CYAN = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_cyan.png");
    private static final Identifier ORANGE = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_orange.png");
    private static final Identifier PINK = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_pink.png");
    private static final Identifier PURPLE = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_purple.png");
    private static final Identifier RED = Identifier.of(Saltcraft.MOD_ID, "textures/entity/jellyfish/jellyfish_red.png");

    public JellyfishRenderer(EntityRendererFactory.Context context) {
        super(context, new JellyfishModel<>(context.getPart(ModModelLayers.JELLYFISH_LAYER)), 0.4f);
    }

    @Override
    public Identifier getTexture(Jellyfish entity) {
        return switch (entity.getVariant()) {
            case BLUE -> BLUE;
            case CYAN -> CYAN;
            case ORANGE -> ORANGE;
            case PINK -> PINK;
            case PURPLE -> PURPLE;
            case RED -> RED;
        };
    }

    @Override
    public void render(Jellyfish entity, float yaw, float tickDelta, MatrixStack matrices,
                       VertexConsumerProvider vertexConsumers, int light) {
        if (entity.isBaby()) {
            matrices.scale(0.5f, 0.5f, 0.5f);
        }
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }
}
