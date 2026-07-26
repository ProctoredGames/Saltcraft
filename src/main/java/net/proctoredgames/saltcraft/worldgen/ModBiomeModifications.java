package net.proctoredgames.saltcraft.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.BiomeTags;
import net.minecraft.util.Identifier;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import net.proctoredgames.saltcraft.Saltcraft;
import net.proctoredgames.saltcraft.entity.ModEntities;
import net.proctoredgames.saltcraft.worldgen.biome.ModBiomes;

public class ModBiomeModifications {
    private static RegistryKey<PlacedFeature> placedFeature(String name) {
        return RegistryKey.of(RegistryKeys.PLACED_FEATURE, Identifier.of(Saltcraft.MOD_ID, name));
    }

    public static void register() {
        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(
                        BiomeKeys.OCEAN, BiomeKeys.DEEP_OCEAN, BiomeKeys.FROZEN_OCEAN, BiomeKeys.COLD_OCEAN,
                        BiomeKeys.LUKEWARM_OCEAN, BiomeKeys.DEEP_COLD_OCEAN, BiomeKeys.DEEP_FROZEN_OCEAN,
                        BiomeKeys.DEEP_LUKEWARM_OCEAN, BiomeKeys.WARM_OCEAN, BiomeKeys.RIVER, BiomeKeys.FROZEN_RIVER,
                        BiomeKeys.SWAMP, BiomeKeys.MANGROVE_SWAMP, ModBiomes.SALT_FLAT),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("salt_ore_placed"));

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_MOUNTAIN),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("pink_salt_ore_placed"));

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.UNDERGROUND_ORES,
                placedFeature("oil_vein_placed"));

        BiomeModifications.addFeature(
                BiomeSelectors.tag(BiomeTags.IS_OVERWORLD),
                GenerationStep.Feature.LOCAL_MODIFICATIONS,
                placedFeature("salt_dome_placed"));

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.SALT_FLAT),
                GenerationStep.Feature.LAKES,
                placedFeature("salt_lake_placed"));

        BiomeModifications.addFeature(
                BiomeSelectors.includeByKey(ModBiomes.SALT_FLAT),
                GenerationStep.Feature.TOP_LAYER_MODIFICATION,
                placedFeature("salt_flat_water_cleanup_placed"));

        BiomeModifications.addSpawn(
                BiomeSelectors.includeByKey(BiomeKeys.WARM_OCEAN),
                SpawnGroup.WATER_CREATURE, ModEntities.JELLYFISH, 10, 2, 4);
    }
}
