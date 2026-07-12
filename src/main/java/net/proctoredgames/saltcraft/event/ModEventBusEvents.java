package net.proctoredgames.saltcraft.event;

import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.animal.AbstractFish;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;
import net.minecraftforge.event.entity.SpawnPlacementRegisterEvent;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.*;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

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

//    @SubscribeEvent
//    public static void registerSpawnPlacement(SpawnPlacementRegisterEvent event) {
//        event.register(ModEntities.JELLYFISH.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
//                AbstractFish::checkMobSpawnRules, SpawnPlacementRegisterEvent.Operation.REPLACE);
//    }

//    @SubscribeEvent
//    public static void onSurfaceRuleData(EvenSurfaceRuleData event) {
//        if (event.getDimensionType().equals(Level.OVERWORLD)) {
//            event.getRules().add(0, ModSurfaceRules.makeRules());
//        }
//    }
}