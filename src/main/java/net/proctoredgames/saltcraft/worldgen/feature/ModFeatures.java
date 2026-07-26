package net.proctoredgames.saltcraft.worldgen.feature;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModFeatures {
    public static final Feature<DefaultFeatureConfig> SALT_DOME = register("salt_dome",
            new SaltDomeFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> SALT_LAKE = register("salt_lake",
            new SaltLakeFeature(DefaultFeatureConfig.CODEC));

    public static final Feature<DefaultFeatureConfig> SALT_FLAT_WATER_CLEANUP = register("salt_flat_water_cleanup",
            new SaltFlatWaterCleanupFeature(DefaultFeatureConfig.CODEC));

    private static <T extends Feature<?>> T register(String name, T feature) {
        return Registry.register(Registries.FEATURE, Identifier.of(Saltcraft.MOD_ID, name), feature);
    }

    public static void register() {
    }
}
