package net.proctoredgames.saltcraft.worldgen.biome;

import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.biome.OverworldBiomes;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.Musics;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.proctoredgames.saltcraft.Saltcraft;

public class ModBiomes {
    public static final DeferredRegister<Biome> BIOMES =
            DeferredRegister.create(ForgeRegistries.BIOMES, Saltcraft.MOD_ID);

    public static final RegistryObject<Biome> SALT_FLAT = BIOMES.register("salt_flat.json",
            () -> new Biome.BiomeBuilder()
                    .hasPrecipitation(false)
                    .temperature(2.0f)
                    .downfall(0.0f)
                    .specialEffects(new BiomeSpecialEffects.Builder()
                            .fogColor(9470285)
                            .waterColor(9470285)
                            .waterFogColor(9470285)
                            .skyColor(9470285)
                            .grassColorOverride(9470285)  // Grass color
                            .foliageColorOverride(10387789) // Foliage color
                            .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                            .backgroundMusic(Musics.createGameMusic(SoundEvents.MUSIC_BIOME_DESERT))
                            .build())
                    .mobSpawnSettings(new MobSpawnSettings.Builder()
                            .addMobCharge(EntityType.SKELETON, 0.5, 0.15)
                            .addSpawn(MobCategory.MONSTER,
                                    new MobSpawnSettings.SpawnerData(
                                            EntityType.SKELETON, 80, 1, 4
                                    ))
                            .build())
                    .generationSettings(BiomeGenerationSettings.EMPTY)
                    .build());

//    private static BiomeGenerationSettings biomeGenerationSettings() {
//        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(,);
//        // Add features here if needed
//        return builder.build();
//    }
}