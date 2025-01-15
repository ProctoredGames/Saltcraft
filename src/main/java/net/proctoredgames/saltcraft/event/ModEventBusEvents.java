package net.proctoredgames.saltcraft.event;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = Saltcraft.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModEventBusEvents {
    @SubscribeEvent
    public static void registerAttributes(EntityAttributeCreationEvent event) {
        event.put(ModEntities.JELLYFISH.get(), Jellyfish.createAttributes().build());
        event.put(ModEntities.CRYSTID.get(), Crystid.createAttributes().build());
        event.put(ModEntities.SALT_MAGE.get(), SaltMage.createAttributes().build());
        event.put(ModEntities.FLAMINGO.get(), Flamingo.createAttributes().build());
        event.put(ModEntities.MIRAGE.get(), Mirage.createAttributes().build());

    }

    @SubscribeEvent
    public static void onEntityAttributeModification(EntityAttributeModificationEvent event){
        event.add(EntityType.TURTLE, Attributes.ATTACK_DAMAGE, 2.0);
    }
}