package net.proctoredgames.saltcraft;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.proctoredgames.saltcraft.client.ClientThirstData;
import net.proctoredgames.saltcraft.client.ThirstHudOverlay;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.client.CrystidModel;
import net.proctoredgames.saltcraft.entity.client.CrystidRenderer;
import net.proctoredgames.saltcraft.entity.client.FlamingoModel;
import net.proctoredgames.saltcraft.entity.client.FlamingoRenderer;
import net.proctoredgames.saltcraft.entity.client.JellyfishModel;
import net.proctoredgames.saltcraft.entity.client.JellyfishRenderer;
import net.proctoredgames.saltcraft.entity.client.MirageModel;
import net.proctoredgames.saltcraft.entity.client.MirageRenderer;
import net.proctoredgames.saltcraft.entity.client.ModModelLayers;
import net.proctoredgames.saltcraft.entity.client.SaltMageModel;
import net.proctoredgames.saltcraft.entity.client.SaltMageRenderer;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.networking.packet.ThirstDataSyncPayload;
import net.proctoredgames.saltcraft.util.ModItemProperties;

public class SaltcraftClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.JELLYFISH_LAYER, JellyfishModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.JELLYFISH, JellyfishRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.CRYSTID_LAYER, CrystidModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.CRYSTID, CrystidRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.SALT_MAGE_LAYER, SaltMageModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SALT_MAGE, SaltMageRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.FLAMINGO_LAYER, FlamingoModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FLAMINGO, FlamingoRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ModModelLayers.MIRAGE_LAYER, MirageModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.MIRAGE, MirageRenderer::new);

        ModItemProperties.addCustomItemProperties();

        FluidRenderHandlerRegistry.INSTANCE.register(ModFluids.OIL, ModFluids.FLOWING_OIL,
                new SimpleFluidRenderHandler(
                        SimpleFluidRenderHandler.WATER_STILL,
                        SimpleFluidRenderHandler.WATER_FLOWING,
                        SimpleFluidRenderHandler.WATER_OVERLAY,
                        0xFF0A0A0A));

        HudRenderCallback.EVENT.register(ThirstHudOverlay.HUD_THIRST);

        ClientPlayNetworking.registerGlobalReceiver(ThirstDataSyncPayload.ID, (payload, context) ->
                context.client().execute(() -> ClientThirstData.set(payload.thirst())));
    }
}
