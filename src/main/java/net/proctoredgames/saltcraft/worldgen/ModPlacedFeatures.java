package net.proctoredgames.saltcraft.worldgen;

import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.placement.*;
import net.proctoredgames.saltcraft.Saltcraft;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.proctoredgames.saltcraft.worldgen.biome.surface.ModSurfaceRules;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> SALT_ORE_PLACED_KEY = registerKey("salt_ore_placed");
    public static final ResourceKey<PlacedFeature> PINK_SALT_ORE_PLACED_KEY = registerKey("pink_salt_ore_placed");
    public static final ResourceKey<PlacedFeature> OIL_VEIN_PLACED_KEY = registerKey("oil_vein_placed");

    public static final ResourceKey<PlacedFeature> SALT_DOME_PLACED_KEY = registerKey("salt_dome_placed");
    public static final ResourceKey<PlacedFeature> SALT_LAKE_PLACED_KEY = registerKey("salt_lake_placed");

    public static void bootstrap(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(12,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));
        register(context, PINK_SALT_ORE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PINK_SALT_ORE_KEY),
                ModOrePlacement.commonOrePlacement(3,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(130), VerticalAnchor.absolute(256))));
        register(context, OIL_VEIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.OIL_VEIN_KEY),
                ModOrePlacement.rareOrePlacement(2,
                        HeightRangePlacement.triangle(VerticalAnchor.absolute(0), VerticalAnchor.absolute(60))));

        register(context, SALT_DOME_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SALT_DOME_KEY),
                List.of(RarityFilter.onAverageOnceEvery(1000), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(0), VerticalAnchor.absolute(0)),
                        BiomeFilter.biome()));

        register(context, SALT_LAKE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SALT_LAKE_KEY),
                List.of(RarityFilter.onAverageOnceEvery(20), InSquarePlacement.spread(),
                        HeightRangePlacement.uniform(VerticalAnchor.absolute(ModSurfaceRules.SALT_FLAT_GROUND_LEVEL), VerticalAnchor.absolute(ModSurfaceRules.SALT_FLAT_GROUND_LEVEL)),
                        BiomeFilter.biome()));

    }


    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Saltcraft.MOD_ID, name));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}