package net.proctoredgames.saltcraft.worldgen.feature;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.GeodeConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.worldgen.feature.SaltDomeFeature;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, "saltcraft");

    // Register the custom blob feature
    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SALT_DOME = FEATURES.register("salt_dome",
            () -> new SaltDomeFeature(NoneFeatureConfiguration.CODEC));

    public static final RegistryObject<Feature<NoneFeatureConfiguration>> SALT_LAKE = FEATURES.register("salt_lake",
            () -> new SaltLakeFeature(NoneFeatureConfiguration.CODEC));


    public static void register(IEventBus eventBus) { FEATURES.register(eventBus); }

}