package net.proctoredgames.saltcraft.event;

import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.client.*;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Saltcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ModEventBusClientEvents {
    @SubscribeEvent
    public static void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(ModModelLayers.JELLYFISH_LAYER, JellyfishModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.CRYSTID_LAYER, CrystidModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.SALT_MAGE_LAYER, SaltMageModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.FLAMINGO_LAYER, FlamingoModel::createBodyLayer);
        event.registerLayerDefinition(ModModelLayers.MIRAGE_LAYER, MirageModel::createBodyLayer);
    }
}