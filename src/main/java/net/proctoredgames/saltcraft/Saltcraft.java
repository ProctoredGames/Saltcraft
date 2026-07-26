package net.proctoredgames.saltcraft;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.WaterCreatureEntity;
import net.minecraft.world.Heightmap;
import net.proctoredgames.saltcraft.block.ModBlocks;
import net.proctoredgames.saltcraft.effect.ModEffects;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.entity.custom.Crystid;
import net.proctoredgames.saltcraft.entity.custom.Flamingo;
import net.proctoredgames.saltcraft.entity.custom.Jellyfish;
import net.proctoredgames.saltcraft.entity.custom.Mirage;
import net.proctoredgames.saltcraft.entity.custom.SaltMage;
import net.proctoredgames.saltcraft.fluid.ModFluids;
import net.proctoredgames.saltcraft.event.ModEvents;
import net.proctoredgames.saltcraft.item.ModItemGroups;
import net.proctoredgames.saltcraft.item.ModItems;
import net.proctoredgames.saltcraft.networking.ModMessages;
import net.proctoredgames.saltcraft.particle.ModParticles;
import net.proctoredgames.saltcraft.potion.ModPotions;
import net.proctoredgames.saltcraft.worldgen.ModBiomeModifications;
import net.proctoredgames.saltcraft.worldgen.feature.ModFeatures;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Saltcraft implements ModInitializer {
    public static final String MOD_ID = "saltcraft";

    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    @Override
    public void onInitialize() {
        ModItemGroups.register();
        ModEntities.register();
        ModFluids.register();
        ModItems.register();
        ModBlocks.register();
        ModEffects.register();
        ModPotions.register();
        ModParticles.register();
        ModFeatures.register();
        ModBiomeModifications.register();

        SpawnRestriction.register(ModEntities.JELLYFISH, SpawnLocationTypes.IN_WATER,
                Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterCreatureEntity::canSpawn);

        FabricDefaultAttributeRegistry.register(ModEntities.JELLYFISH, Jellyfish.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.CRYSTID, Crystid.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.SALT_MAGE, SaltMage.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.FLAMINGO, Flamingo.createAttributes());
        FabricDefaultAttributeRegistry.register(ModEntities.MIRAGE, Mirage.createAttributes());

        FuelRegistry.INSTANCE.add(ModItems.OIL_BUCKET, 10000);

        ModMessages.register();
        ModEvents.register();
    }
}
